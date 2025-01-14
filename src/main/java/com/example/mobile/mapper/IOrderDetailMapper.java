package com.example.mobile.mapper;

import com.example.mobile.dto.request.OrderDetailReq;
import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.entity.ImageProduct;
import com.example.mobile.entity.Orderdetail;
import com.example.mobile.repository.ImageProductRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class IOrderDetailMapper {

    @Autowired
    private ImageProductRepository imageProductRepository;

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    public abstract OrderDetailResponse toResponse(Orderdetail orderDetail);

    // Chuyển từ OrderDetailReq sang OrderDetail Entity
    @Mapping(source = "productId", target = "product.id")
    public abstract Orderdetail toEntity(OrderDetailReq orderDetailReq);

    @AfterMapping
    protected void addImageFromRepository(@MappingTarget OrderDetailResponse response, Orderdetail orderDetail) {
        if (orderDetail.getProduct() != null) {
            Integer productId = orderDetail.getProduct().getId();
            List<ImageProduct> imageProducts = imageProductRepository.findAllImagesByProductId(productId);

            // Lấy ảnh đầu tiên nếu có
            if (!imageProducts.isEmpty()) {
                response.setImage(imageProducts.get(0).getLinkImage());
            } else {
                response.setImage(null);
            }
            response.setPrice(orderDetail.getProduct().getPrice());
        }
    }

    // Sử dụng orderdetails thay vì orderDetails để ánh xạ đúng trường trong entity
    public abstract Set<OrderDetailResponse> toResponseList(Set<Orderdetail> orderDetails);
}
