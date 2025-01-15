package com.example.mobile.service;

import com.example.mobile.dto.request.OrderDetailReq;
import com.example.mobile.dto.request.OrderReq;
import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.entity.*;
import com.example.mobile.mapper.IOrderDetailMapper;
import com.example.mobile.mapper.IOrderMapper;
import com.example.mobile.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    private final ImageProductRepository imageProductRepository;

    OrderRepository orderRepository;
     ProductRepository productRepository;
     VoucherRepository voucherRepository;
     PaymentMethodRepository paymentMethodRepository;
     UserRepository userRepository;
     IOrderMapper orderMapper;
     IOrderDetailMapper orderDetailMapper;
     CartRepository cartRepository;
     OrderDetailRepository orderDetailRepository;

    public List<OrderDetailResponse> viewDetail(int orderId) {
        List<Orderdetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);

        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No Orderdetails found for orderId: " + orderId);
        }

        List<OrderDetailResponse> responseList = new ArrayList<>();

        for (Orderdetail orderDetail : orderDetails) {
            List<ImageProduct> imageProductList = imageProductRepository.findAllImagesByProductId(orderDetail.getProduct().getId());
            List<String> images = new ArrayList<>();
            for (ImageProduct image : imageProductList) {
                images.add(image.getLinkImage());
            }

            String image = (images.isEmpty()) ? null : images.get(0);

            OrderDetailResponse response = OrderDetailResponse.builder()
                    .id(orderDetail.getId())
                    .orderId(orderDetail.getOrder() != null ? orderDetail.getOrder().getId() : null)
                    .productId(orderDetail.getProduct() != null ? orderDetail.getProduct().getId() : null)
                    .image(image)
                    .productName(orderDetail.getProduct() != null ? orderDetail.getProduct().getName() : null)
                    .price(orderDetail.getPrice())
                    .amount(orderDetail.getAmount())
                    .quantity(orderDetail.getQuantity())
                    .build();

            responseList.add(response);
        }

        return responseList;
    }

    public Boolean updateOrderStatus(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with orderId: " + orderId));
        order.setStatus("DONE");
        orderRepository.save(order);
        return true;
    }

    public List<OrderResponse> getListOrderHistory() {
        String status = "DONE";
        List<Order> orderList = orderRepository.findByStatus(status);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order o : orderList) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .id(o.getId())
                    .userId(o.getUser().getId())
                    .username(o.getUser().getUsername())
                    .fullname(o.getUser().getFirstname() + " " + o.getUser().getLastname())
                    .voucherId(o.getVoucher() != null ? o.getVoucher().getId() : null)
                    .discount(o.getVoucher() != null ? o.getVoucher().getValueDiscount() : 0)
                    .totalProduct(o.getTotalProduct())
                    .amount(o.getAmount())
                    .status(o.getStatus())
                    .createAt(o.getCreateAt())
                    .build();
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    public List<OrderResponse> getListOrderShip() {
        String status = "PENDING";
        List<Order> orderList =  orderRepository.findByStatus(status);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order o : orderList) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .id(o.getId())
                    .userId(o.getUser().getId())
                    .username(o.getUser().getUsername())
                    .fullname(o.getUser().getFirstname() + " " + o.getUser().getLastname())
                    .voucherId(o.getVoucher() != null ? o.getVoucher().getId() : null)
                    .discount(o.getVoucher() != null ? o.getVoucher().getValueDiscount() : 0)
                    .totalProduct(o.getTotalProduct())
                    .amount(o.getAmount())
                    .status(o.getStatus())
                    .createAt(o.getCreateAt())
                    .build();
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    private User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }


    @Transactional
    public OrderResponse createOrder(OrderReq orderReq) {
        PaymentMethod paymentMethod = validatePaymentMethod(orderReq.getPaymentMethodId());

        User currentUser = getCurrentUser();

        Order order = createAndCalculateOrder(orderReq, paymentMethod, currentUser);

        Order savedOrder = orderRepository.save(order);

        List<Integer> productIds = new ArrayList<>();
        for (Orderdetail orderdetail : savedOrder.getOrderdetails()) {
            productIds.add(orderdetail.getProduct().getId());
        }

        cartRepository.deleteAllByProductIds(productIds);

        return createOrderResponse(savedOrder);
    }


    private PaymentMethod validatePaymentMethod(Integer paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment method ID"));
    }


    private Order createAndCalculateOrder(OrderReq orderReq, PaymentMethod paymentMethod, User currentUser) {
        Order order = new Order();
        order.setPaymentMethod(paymentMethod);
        order.setCreateAt(Instant.now());
        order.setStatus("PENDING");
        order.setUser(currentUser);

        double totalAmount = 0.0;
        int totalProduct = 0;
        Set<Orderdetail> orderDetails = new HashSet<>();

        for (OrderDetailReq detailReq : orderReq.getOrderDetails()) {
            Orderdetail orderDetail = createOrderDetail(detailReq); // Tạo OrderDetail
            orderDetail.setOrder(order); // Gán Order cho OrderDetail
            orderDetails.add(orderDetail); // Thêm OrderDetail vào Set
            totalAmount += orderDetail.getAmount();
            totalProduct += orderDetail.getQuantity();
        }

        totalAmount = applyVoucherIfPresent(orderReq.getVoucherId(), totalAmount);

        order.setTotalProduct(totalProduct);
        order.setAmount(totalAmount);

        order.setOrderdetails(orderDetails);

        return order;
    }



    private Orderdetail createOrderDetail(OrderDetailReq detailReq) {
        Product product = productRepository.findById(detailReq.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        int quantity = detailReq.getQuantity();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        double price = product.getPrice();
        double amount = price * quantity;

        Orderdetail orderDetail = new Orderdetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(price);
        orderDetail.setAmount(amount);

        return orderDetail;
    }

    private double applyVoucherIfPresent(Integer voucherId, double totalAmount) {
        if (voucherId != null) {
            Voucher voucher = voucherRepository.findById(voucherId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid voucher ID"));

            totalAmount -= voucher.getValueDiscount();
            totalAmount = Math.max(0, totalAmount);
        }
        return totalAmount;
    }

    private OrderResponse createOrderResponse(Order savedOrder) {
        OrderResponse response = new OrderResponse();
        response.setId(savedOrder.getId());
        response.setAmount(savedOrder.getAmount());
        response.setTotalProduct(savedOrder.getTotalProduct());
        response.setStatus(savedOrder.getStatus());
        response.setCreateAt(savedOrder.getCreateAt());

        Set<OrderDetailResponse> orderDetailResponses = new HashSet<>();
        for (Orderdetail orderDetail : savedOrder.getOrderdetails()) {
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            orderDetailResponse.setProductId(orderDetail.getProduct().getId());
            orderDetailResponse.setQuantity(orderDetail.getQuantity());
            orderDetailResponse.setPrice(orderDetail.getPrice());
            orderDetailResponse.setAmount(orderDetail.getAmount());
            orderDetailResponses.add(orderDetailResponse);
        }

        response.setOrderDetails(orderDetailResponses);
        return response;
    }






    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse response = orderMapper.toResponse(order);
            response.setOrderDetails(orderDetailMapper.toResponseList(order.getOrderdetails()));
            orderResponses.add(response);
        }
        return orderResponses;
    }

    // Phương thức lấy đơn hàng theo ID
    public OrderResponse getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

        OrderResponse response = orderMapper.toResponse(order);
        response.setOrderDetails(orderDetailMapper.toResponseList(order.getOrderdetails()));
        return response;
    }
}
