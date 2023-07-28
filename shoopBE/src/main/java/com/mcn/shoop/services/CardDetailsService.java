package com.mcn.shoop.services;

import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.repositories.CardDetailsRepository;
import com.mcn.shoop.validators.CardDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardDetailsService {
    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public List<CardDetails> getCard(){
        return cardDetailsRepository.findAll();
    }

    public CardDetails createCardDetails(CardDetails cardDetails){
        return cardDetailsRepository.save(cardDetails);
    }

    public CardDetails updateCardDetails(Long id, CardDetails cardDetails){
        CardDetailsValidator.validateCard(cardDetails);
        CardDetails currentCard = cardDetailsRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCard.setCardNumber(cardDetails.getCardNumber());
        currentCard.setExpirationDate(cardDetails.getExpirationDate());
        currentCard.setNameCard(cardDetails.getNameCard());
        currentCard.setCvv(cardDetails.getCvv());

        return cardDetailsRepository.save(cardDetails);
    }

    public void deleteCardDetails(Long id){
        cardDetailsRepository.deleteById(id);
    }
}
