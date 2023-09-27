package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.VoucherDTO;
import com.mcn.shoop.entities.Voucher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherStructMapper {
    VoucherDTO voucherToVoucherDto(Voucher voucher);
    Voucher voucherDtoToVoucher(VoucherDTO voucherDTO);
}
