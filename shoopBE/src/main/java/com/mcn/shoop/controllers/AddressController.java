package com.mcn.shoop.controllers;


import com.mcn.shoop.entities.Address;
import com.mcn.shoop.repositories.AddressRepository;
import com.mcn.shoop.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    private final AddressService addressService;
    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> list() {
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressRepository.findById(id).orElseThrow(RuntimeException::new);
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
    public void deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
    }
}
