package com.example.mobile.service;
import com.example.mobile.dto.request.VoucherCreationReq;
import com.example.mobile.dto.request.VoucherUpdateReq;
import com.example.mobile.dto.response.VoucherResponse;
import com.example.mobile.entity.Voucher;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IVoucherMapper;
import com.example.mobile.repository.VoucherRepository;
import com.example.mobile.service.imp.IVoucher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VoucherService implements IVoucher {
    VoucherRepository voucherRepository;
    IVoucherMapper voucherMapper;

    @Override
    public VoucherResponse addVoucher(VoucherCreationReq req) {

        if (voucherRepository.existsById(req.getId())) {
            throw new AddException(ErrorCode.VOUCHER_EXISTED);
        }
        Voucher voucher = voucherMapper.toVoucher(req);
        return voucherMapper.toVoucherResponse(voucherRepository.save(voucher));

    }

    @Override
    public List<VoucherResponse> getListVoucher() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return voucherRepository.findAll().stream()
                .map(voucherMapper::toVoucherResponse).toList();
    }

    @Override
    public VoucherResponse findVoucherById(int id) {
        return voucherMapper.toVoucherResponse(voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Voucher not found!")));
    }

    @Override
    public VoucherResponse voucherUpdate(int id, VoucherUpdateReq req) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher not found!"));

        voucherMapper.updateVoucher(voucher, req);   // Use MappingTarget to mapping data update from req (new info) into old info

        return voucherMapper.toVoucherResponse(voucherRepository.save(voucher));
    }

    @Override
    public void deleteVoucher(int id) {
        voucherRepository.deleteById(id);
    }

}
