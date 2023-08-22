package com.mcn.shoop.services;

import com.mcn.shoop.entities.Voucher;
import com.mcn.shoop.repositories.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VouchersService {
    private final VouchersRepository vouchersRepository;

    @Autowired
    public VouchersService(VouchersRepository vouchersRepository){
        this.vouchersRepository = vouchersRepository;
    }

    public List<Voucher> getVoucherss(){
        return vouchersRepository.findAll();
    }

    public Voucher getVouchers(Long id){
        return vouchersRepository.findById(id).orElse(null);
    }

    public Voucher createVouchers(Voucher vouchers){
        return vouchersRepository.save(vouchers);
    }

    public Voucher updateVouchers(Long id, Voucher vouchers){
        Voucher currentVouchers = vouchersRepository.findById(id).orElseThrow(RuntimeException::new);
        currentVouchers.setCod(vouchers.getCod());

        return vouchersRepository.save(vouchers);
    }

    public void deleteVouchers(Long id){
        vouchersRepository.deleteById(id);
    }
}
