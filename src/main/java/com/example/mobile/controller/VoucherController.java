package com.example.mobile.controller;

import com.example.mobile.dto.request.VoucherCreationReq;
import com.example.mobile.dto.request.VoucherUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.VoucherResponse;
import com.example.mobile.service.imp.IVoucher;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/list/shop/{shopId}")
    ApiResponse<List<VoucherResponse>> getListVoucherByShopId(@PathVariable("shopId") int shopId) {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getListVoucherByShopId(shopId))
                .build();
    }

    @GetMapping("/findId/{voucherId}")
    VoucherResponse getUser(@PathVariable("voucherId") int voucherId) {
        return voucherService.findVoucherById(voucherId);
    }

    @PutMapping("/update/{voucherId}")
    ApiResponse<VoucherResponse> updateVoucher(@PathVariable("voucherId") int voucherId, @RequestBody VoucherUpdateReq voucherUpdateReq) {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.voucherUpdate(voucherId,voucherUpdateReq))
                .build();

    }

    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("voucherId") int voucherId) {
        try {
            voucherService.deleteVoucher(voucherId); // Xóa voucher
            return ResponseEntity.ok("Voucher has been deleted!"); // Trả về 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voucher not found!"); // Trả về 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the voucher."); // Trả về 500
        }
    }

}
