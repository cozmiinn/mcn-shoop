package com.mcn.shoop.services;

import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.mappers.CardStructMapper;
import com.mcn.shoop.repositories.CardDetailsRepository;
import com.mcn.shoop.validators.CardDetailsValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardDetailsService {
    private final CardDetailsRepository cardDetailsRepository;
    private final CardStructMapper cardStructMapper;

    @Autowired
    private CardDetailsService(CardDetailsRepository cardDetailsRepository, CardStructMapper cardStructMapper){
        this.cardDetailsRepository = cardDetailsRepository;
        this.cardStructMapper = cardStructMapper;
    }


    public List<CardDetailsDTO> getCards(){
        List<CardDetails> getCards = cardDetailsRepository.findAll();
        List<CardDetailsDTO> cardDetailsDTOS = getCards
                .stream()
                .map(cardStructMapper::cardToCardDto)
                .collect(Collectors.toList());
        return cardDetailsDTOS;
    }

    public CardDetailsDTO getCard(Long id){
        CardDetails getCaard = cardDetailsRepository.findById(id).orElse(null);
        return cardStructMapper.cardToCardDto(getCaard);
    }

    public CardDetailsDTO createCardDetails(CardDetailsDTO cardDetailsDTO){
        CardDetails cardDetails1 = cardStructMapper.cardDtoToCard(cardDetailsDTO);
        CardDetails create = cardDetailsRepository.save(cardDetails1);
        return cardStructMapper.cardToCardDto(create);
    }

    public CardDetailsDTO updateCardDetails(Long id, CardDetailsDTO cardDetailsDTO){
        CardDetailsValidator.validateCard(cardDetailsDTO);

        CardDetails currentCard = cardDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Card details with id " + id + " not found!"));
        currentCard.setCardNumber(cardDetailsDTO.getCardNumber());
        currentCard.setExpirationDate(cardDetailsDTO.getExpirationDate());
        currentCard.setNameCard(cardDetailsDTO.getNameCard());
        currentCard.setCvv(cardDetailsDTO.getCvv());

        currentCard = cardDetailsRepository.save(currentCard);

        return cardStructMapper.cardToCardDto(currentCard);
    }

    public void deleteCardDetails(Long id){
        cardDetailsRepository.deleteById(id);
    }
}
