package com.example.mobile.controller;

import com.example.mobile.dto.request.VoucherCreationReq;
import com.example.mobile.dto.request.VoucherUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.VoucherResponse;
import com.example.mobile.service.imp.IVoucher;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vouchers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherController {
    IVoucher voucherService;
    @PostMapping("/add")
    ApiResponse<VoucherResponse> addVoucher(@RequestBody @Valid VoucherCreationReq voucherCreationReq) {
        ApiResponse<VoucherResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(voucherService.addVoucher(voucherCreationReq));
        return apiResponse;
    }
    @GetMapping("/listVoucher")
    ApiResponse<List<VoucherResponse>> getListVoucher() {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getListVoucher())
                .build();
    }
    @GetMapping("/{voucherId}")
    VoucherResponse getUser(@PathVariable("voucherId") int voucherId) {
        return voucherService.findVoucherById(voucherId);
    }
    @PutMapping("/{voucherId}")
    VoucherResponse updateVoucher(@PathVariable("voucherId") int voucherId, @RequestBody VoucherUpdateReq voucherUpdateReq) {
        return voucherService.voucherUpdate(voucherId,voucherUpdateReq);
    }
    @DeleteMapping("/{voucherId}")
    String deleteVoucher(@PathVariable("voucherId") int voucherId) {
        voucherService.deleteVoucher(voucherId);
        return "Voucher has been deleted!";
    }
}
