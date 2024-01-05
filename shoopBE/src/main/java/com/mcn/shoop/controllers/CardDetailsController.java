package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.services.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/users/cards")
public class CardDetailsController {
    private final CardDetailsService cardDetailsService;

    @Autowired
    public CardDetailsController(CardDetailsService cardDetailsService) {
        this.cardDetailsService = cardDetailsService;
    }

    @GetMapping
    public List<CardDetailsDTO> list(){
        return cardDetailsService.getCards();
    }

    @GetMapping("/find/{id}")
    public CardDetailsDTO getCardDetails(@PathVariable Long id){
        return cardDetailsService.getCard(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CardDetailsDTO> createCardDetails(@RequestBody CardDetailsDTO cardDetailsDTO){
        CardDetailsDTO saveCardDetails = cardDetailsService.createCardDetails(cardDetailsDTO);
        return ResponseEntity.ok(saveCardDetails);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardDetailsDTO> updateCardDetails(@PathVariable Long id, @RequestBody CardDetailsDTO cardDetailsDTO){
        CardDetailsDTO updateCardDetails = cardDetailsService.updateCardDetails(id, cardDetailsDTO);
        return ResponseEntity.ok(updateCardDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        cardDetailsService.deleteCardDetails(id);
    }
}
