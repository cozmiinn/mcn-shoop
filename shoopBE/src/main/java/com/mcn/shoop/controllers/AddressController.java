package com.mcn.shoop.controllers;


import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/users/addresses")
public class AddressController {

    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> list() {
        return addressService.getAddresss();
    }

    @GetMapping("/find/{id}")
    public AddressDTO getAddress(@PathVariable Long id) {
       return addressService.getAddress(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){
        AddressDTO savedAddress = addressService.createAddress(addressDTO);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updateAddress = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(updateAddress);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address is deleted successfully", HttpStatus.OK);    }
}
