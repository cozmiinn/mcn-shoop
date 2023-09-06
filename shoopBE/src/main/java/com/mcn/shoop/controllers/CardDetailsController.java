package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.services.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/card")
public class CardDetailsController {
    private final CardDetailsService cardDetailsService;

    @Autowired
    public CardDetailsController(CardDetailsService cardDetailsService) {
        this.cardDetailsService = cardDetailsService;
    }

    @GetMapping
    public List<CardDetails> list(){
        return cardDetailsService.getCards();
    }

    @GetMapping("/{id}")
    public CardDetails getCardDetails(@PathVariable Long id){
        return cardDetailsService.getCard(id);
    }

    @PostMapping
    public ResponseEntity<CardDetails> createCardDetails(@RequestBody CardDetails cardDetails){
        CardDetails saveCardDetails = cardDetailsService.createCardDetails(cardDetails);
        return ResponseEntity.ok(saveCardDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDetails> updateCardDetails(@PathVariable Long id, @RequestBody CardDetails cardDetails){
        CardDetails updateCardDetails = cardDetailsService.updateCardDetails(id, cardDetails);
        return ResponseEntity.ok(updateCardDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        cardDetailsService.deleteCardDetails(id);
    }
}
