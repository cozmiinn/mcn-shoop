package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.services.CartEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.concurrent.java8.FuturesConvertersImpl;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/entry")
public class CartEntryController {
    private final CartEntryService cartEntryService;

    @Autowired
    public CartEntryController(CartEntryService cartEntryService) {
        this.cartEntryService = cartEntryService;
    }

    @GetMapping
    public List<CartEntry> list(){
        return cartEntryService.getCartEntrys();
    }

    @GetMapping("/{id}")
    public CartEntry getCartEntry(@PathVariable Long id){
        return cartEntryService.getCartEntry(id);
    }

    @PostMapping
    public ResponseEntity<CartEntry> createCartEntry(@RequestBody CartEntry cartEntry){
        CartEntry savedCartEntry = cartEntryService.createCartEntry(cartEntry);
        return ResponseEntity.ok(savedCartEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartEntry> updateCartEntry(@PathVariable Long id, @RequestBody CartEntry cartEntry){
        CartEntry updateCartEntry = cartEntryService.updateCartEntry(id, cartEntry);
        return ResponseEntity.ok(updateCartEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartEntry(@PathVariable("id") Long id){
        cartEntryService.deleteCartEntry(id);
        return new ResponseEntity<>("Cart is deleted succesfully!", HttpStatus.OK);
    }
}
