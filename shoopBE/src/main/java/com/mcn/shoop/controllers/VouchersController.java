package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Voucher;
import com.mcn.shoop.services.VouchersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/products/vouchers")
public class VouchersController {
    private final VouchersService vouchersService;

    @Autowired
    public VouchersController(VouchersService vouchersService) {
        this.vouchersService = vouchersService;
    }

    @GetMapping
    public List<Voucher> list(){
        return vouchersService.getVoucherss();
    }

    @GetMapping("/{id}")
    public Voucher getVouchers(@PathVariable Long id){
        return vouchersService.getVouchers(id);
    }

    @PostMapping
    public ResponseEntity<Voucher> createVouchers(@RequestBody Voucher vouchers){
        Voucher savedVouchers = vouchersService.createVouchers(vouchers);
        return ResponseEntity.ok(savedVouchers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVouchers(@PathVariable Long id, @RequestBody Voucher vouchers){
        Voucher updateVouchers = vouchersService.updateVouchers(id, vouchers);
        return ResponseEntity.ok(updateVouchers);
    }
}
