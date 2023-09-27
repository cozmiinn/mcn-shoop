package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.CartDTO;
import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.dtos.VoucherDTO;
import com.mcn.shoop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/user/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartDTO> list(){
        return cartService.getCarts();
    }

    @GetMapping("/find/{id}")
    public CartDTO getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO){
        CartDTO savedCart = cartService.createCart(cartDTO);
        return ResponseEntity.ok(savedCart);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO){
        CartDTO updateCart = cartService.updateCart(id, cartDTO);
        return ResponseEntity.ok(updateCart);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
        return new ResponseEntity<>("Cart is deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/{id}/entry/{entryId}")
    public ResponseEntity<Object> addEntryToCart(@PathVariable("id") Long id, @PathVariable("entryId") Long entryId, CartEntryDTO cartEntryDTO){
       return cartService.addEntryToCart(id, entryId, cartEntryDTO);
    }

    @PostMapping("/{id}/user/{userId}")
    public ResponseEntity<Object> addCartToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId, UserDTO userDTO){
       return cartService.addCartToUser(id, userId, userDTO);
    }

    @PostMapping("/{id}/voucher/{voucherId}")
    public ResponseEntity<String> addVoucherToCart(@PathVariable("id") Long id, @PathVariable("voucherId") Long voucherId, VoucherDTO voucherDTO){
        return cartService.addVoucherToCart(id, voucherId, voucherDTO);
    }
}
