package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> list() {
        return userService.getUsers();
    }

    @GetMapping("/find/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<AddressDTO> addAddressToUser(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO address = userService.addAddressToUser(id, addressDTO);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<CardDetailsDTO> addCardToUser(@PathVariable("id") Long id, @RequestBody CardDetailsDTO cardDetailsDTO){
        CardDetailsDTO cardDetails = userService.addCardToUser(id, cardDetailsDTO);
        return new ResponseEntity<>(cardDetails, HttpStatus.OK);
    }
}