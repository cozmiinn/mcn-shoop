package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Cart;
import com.mcn.shoop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> list(){
        return cartService.getCarts();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        Cart savedCart = cartService.createCart(cart);
        return ResponseEntity.ok(savedCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart){
        Cart updateCart = cartService.updateCart(id, cart);
        return ResponseEntity.ok(updateCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
        return new ResponseEntity<>("Cart is deleted succesfully!", HttpStatus.OK);
    }

    @PostMapping("/{id}/{entryId}/entry")
    public ResponseEntity<Object> addEntryToCart(@PathVariable("id") Long id, @PathVariable("entryId") Long entryId){
        return cartService.addEntryToCart(id, entryId);
    }

    @PostMapping("/{id}/{userId}/user")
    public ResponseEntity<Object> addCartToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId){
        return cartService.addCartToUser(id, userId);
    }

    @PostMapping("/{id}/{voucherId}/voucher")
    public ResponseEntity<Object> addVoucherToCart(@PathVariable("id") Long id, @PathVariable("voucherId") Long voucherId){
        return cartService.addVoucherToCart(id, voucherId);
    }
}
