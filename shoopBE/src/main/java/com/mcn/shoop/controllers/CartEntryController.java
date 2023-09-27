package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.services.CartEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("user/cart/entry")
public class CartEntryController {
    private final CartEntryService cartEntryService;

    @Autowired
    public CartEntryController(CartEntryService cartEntryService) {
        this.cartEntryService = cartEntryService;
    }

    @GetMapping
    public List<CartEntryDTO> list(){
        return cartEntryService.getCartEntrys();
    }

    @GetMapping("/find/{id}")
    public CartEntryDTO getCartEntry(@PathVariable Long id){
        return cartEntryService.getCartEntry(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CartEntryDTO> createCartEntry(@RequestBody CartEntryDTO cartEntryDTO){
        CartEntryDTO savedCartEntry = cartEntryService.createCartEntry(cartEntryDTO);
        return ResponseEntity.ok(savedCartEntry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartEntryDTO> updateCartEntry(@PathVariable Long id, @RequestBody CartEntryDTO cartEntryDTO){
        CartEntryDTO updateCartEntry = cartEntryService.updateCartEntry(id, cartEntryDTO);
        return ResponseEntity.ok(updateCartEntry);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartEntry(@PathVariable("id") Long id){
        cartEntryService.deleteCartEntry(id);
        return new ResponseEntity<>("Cart is deleted successfully!", HttpStatus.OK);
    }
}
