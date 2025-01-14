package com.example.mobile.service;

import com.example.mobile.dto.request.OrderDetailReq;
import com.example.mobile.dto.request.OrderReq;
import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.entity.*;
import com.example.mobile.mapper.IOrderDetailMapper;
import com.example.mobile.mapper.IOrderMapper;
import com.example.mobile.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IOrderDetailMapper orderDetailMapper;

    @Autowired
    private CartRepository cartRepository;

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
