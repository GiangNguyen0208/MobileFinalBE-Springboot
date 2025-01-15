package com.example.mobile.mapper;

import com.example.mobile.dto.request.VoucherCreationReq;
import com.example.mobile.dto.request.VoucherUpdateReq;
import com.example.mobile.dto.response.VoucherResponse;
import com.example.mobile.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IVoucherMapper {
    Voucher toVoucher(VoucherCreationReq req);

    @Mapping(target = "shopName", source = "shop.name")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "id", source = "id")
    VoucherResponse toVoucherResponse(Voucher voucher);
    void updateVoucher(@MappingTarget Voucher voucher, VoucherUpdateReq req);

}
