package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.VoucherDTO;
import com.mcn.shoop.services.VouchersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/user/cart/vouchers")
public class VouchersController {
    private final VouchersService vouchersService;

    @Autowired
    public VouchersController(VouchersService vouchersService) {
        this.vouchersService = vouchersService;
    }

    @GetMapping
    public List<VoucherDTO> list(){
        return vouchersService.getVoucherss();
    }

    @GetMapping("/find/{id}")
    public VoucherDTO getVouchers(@PathVariable Long id){
        return vouchersService.getVouchers(id);
    }

    @PostMapping("/add")
    public ResponseEntity<VoucherDTO> createVouchers(@RequestBody VoucherDTO voucherDTO){
        VoucherDTO savedVouchers = vouchersService.createVouchers(voucherDTO);
        return ResponseEntity.ok(savedVouchers);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VoucherDTO> updateVouchers(@PathVariable Long id, @RequestBody VoucherDTO voucherDTO){
        VoucherDTO updateVouchers = vouchersService.updateVouchers(id, voucherDTO);
        return ResponseEntity.ok(updateVouchers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVouchers(@PathVariable("id") Long id){
        vouchersService.deleteVouchers(id);
        return new ResponseEntity<>("Voucher is deleted successfully!", HttpStatus.OK);
    }
}
