package com.mcn.shoop.services;

import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.mappers.CartEntryStructMapper;
import com.mcn.shoop.repositories.CartEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartEntryService {
    private final CartEntryRepository cartEntryRepository;
    private final CartEntryStructMapper cartEntryStructMapper;

    @Autowired
    public CartEntryService(CartEntryRepository cartEntryRepository, CartEntryStructMapper cartEntryStructMapper) {
        this.cartEntryRepository = cartEntryRepository;
        this.cartEntryStructMapper = cartEntryStructMapper;
    }

    public List<CartEntryDTO> getCartEntrys(){
        List<CartEntry> getCartEntrys = cartEntryRepository.findAll();
        List<CartEntryDTO> cartEntryDTOS;
        cartEntryDTOS = getCartEntrys
                .stream()
                .map(cartEntryStructMapper::cartEntryToCartEntryDto)
                .collect(Collectors.toList());
        return cartEntryDTOS;
    }

    public CartEntryDTO getCartEntry(Long id){
        CartEntry getCarEntry = cartEntryRepository.findById(id).orElse(null);
        return cartEntryStructMapper.cartEntryToCartEntryDto(getCarEntry);
    }

    public CartEntryDTO createCartEntry(CartEntryDTO cartEntryDTO){
        CartEntry cartEntry = cartEntryStructMapper.cartEntryDtoToCartEntry(cartEntryDTO);
        CartEntry create = cartEntryRepository.save(cartEntry);
        return cartEntryStructMapper.cartEntryToCartEntryDto(create);
    }

    public CartEntryDTO updateCartEntry(Long id, CartEntryDTO cartEntryDTO){
        CartEntry currentCartEntry = cartEntryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found!"));
        currentCartEntry.setQuantity(cartEntryDTO.getQuantity());
        currentCartEntry.setPricePerPiece(cartEntryDTO.getPricePerPiece());
        currentCartEntry.setTotalPricePerEntry(cartEntryDTO.getTotalPricePerEntry());
        currentCartEntry = cartEntryRepository.save(currentCartEntry);

        return cartEntryStructMapper.cartEntryToCartEntryDto(currentCartEntry);
    }
    public void deleteCartEntry(Long id){
        cartEntryRepository.deleteById(id);
    }

}



