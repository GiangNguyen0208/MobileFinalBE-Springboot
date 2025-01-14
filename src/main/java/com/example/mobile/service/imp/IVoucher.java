package com.example.mobile.service.imp;

import com.example.mobile.dto.request.VoucherCreationReq;
import com.example.mobile.dto.request.VoucherUpdateReq;
import com.example.mobile.dto.response.VoucherResponse;

import java.util.List;

public interface IVoucher {
    VoucherResponse addVoucher(VoucherCreationReq req);
    List<VoucherResponse> getListVoucher();
    VoucherResponse findVoucherById(int id);
    VoucherResponse voucherUpdate(int id, VoucherUpdateReq req);
    void deleteVoucher(int id);
    List<VoucherResponse> getListVoucherByShopId(int id);
}
