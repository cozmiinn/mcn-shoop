package com.mcn.shoop.services;

import com.mcn.shoop.dtos.VoucherDTO;
import com.mcn.shoop.entities.Voucher;
import com.mcn.shoop.mappers.VoucherStructMapper;
import com.mcn.shoop.repositories.VouchersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.core.DummyInvocationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VouchersService {
    private final VouchersRepository vouchersRepository;
    private final VoucherStructMapper voucherStructMapper;

    @Autowired
    public VouchersService(VouchersRepository vouchersRepository, VoucherStructMapper voucherStructMapper){
        this.vouchersRepository = vouchersRepository;
        this.voucherStructMapper = voucherStructMapper;
    }

    public List<VoucherDTO> getVoucherss(){
        List<Voucher> getVouchers = vouchersRepository.findAll();
        List<VoucherDTO> voucherDTOS;
        voucherDTOS = getVouchers
                .stream()
                .map(voucherStructMapper::voucherToVoucherDto)
                .collect(Collectors.toList());
        return voucherDTOS;

    }

    public VoucherDTO getVouchers(Long id){
        Voucher getVouchers = vouchersRepository.findById(id).orElse(null);
        return voucherStructMapper.voucherToVoucherDto(getVouchers);
    }

    public VoucherDTO createVouchers(VoucherDTO voucherDTO){
        Voucher voucher = voucherStructMapper.voucherDtoToVoucher(voucherDTO);
        Voucher create = vouchersRepository.save(voucher);
        return voucherStructMapper.voucherToVoucherDto(create);
    }

    public VoucherDTO updateVouchers(Long id, VoucherDTO voucherDTO){
        Voucher currentVouchers = vouchersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voucher with id " + id + " not found!"));
        currentVouchers.setCod(voucherDTO.getCod());
        currentVouchers.setClientName(voucherDTO.getClientName());
        currentVouchers.setDiscount(voucherDTO.getDiscount());

        currentVouchers = vouchersRepository.save(currentVouchers);

        return voucherStructMapper.voucherToVoucherDto(currentVouchers);
    }

    public void deleteVouchers(Long id){
        vouchersRepository.deleteById(id);
    }
}
