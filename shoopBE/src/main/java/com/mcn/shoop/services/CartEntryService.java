package com.mcn.shoop.services;

import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.repositories.CartEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartEntryService {
    private final CartEntryRepository cartEntryRepository;

    @Autowired
    public CartEntryService(CartEntryRepository cartEntryRepository) {
        this.cartEntryRepository = cartEntryRepository;
    }

    public List<CartEntry> getCartEntrys(){
        return cartEntryRepository.findAll();
    }

    public CartEntry getCartEntry(Long id){
        return cartEntryRepository.findById(id).orElse(null);
    }

    public CartEntry createCartEntry(CartEntry cartEntry){
        return cartEntryRepository.save(cartEntry);
    }

    public CartEntry updateCartEntry(Long id, CartEntry cartEntry){
        CartEntry currentCartEntry = cartEntryRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCartEntry.setQuantity(cartEntry.getQuantity());
        currentCartEntry.setPricePerPiece(cartEntry.getPricePerPiece());
        currentCartEntry.setTotalPricePerEntry(cartEntry.getTotalPricePerEntry());

        return cartEntryRepository.save(currentCartEntry);
    }
    public void deleteCartEntry(Long id){
        cartEntryRepository.deleteById(id);
    }

}



