package com.mcn.shoop.controllers;


import com.mcn.shoop.entities.Address;
import com.mcn.shoop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> list() {
        return addressService.getAddresss();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
       return addressService.getAddress(id);
    }


    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address savedAddress = addressService.createAddress(address);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updateAddress = addressService.updateAddress(id, address);
        return ResponseEntity.ok(updateAddress);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address is deleted successfully!", HttpStatus.OK);
    }
}
