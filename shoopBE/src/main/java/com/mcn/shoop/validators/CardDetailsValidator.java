package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.entities.CardDetails;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CardDetailsValidator {

    public static void validateCard(CardDetailsDTO cardDetailsDTO){
        if(cardDetailsDTO.getCardNumber() == -1){
            throw new IllegalArgumentException("Card details is required!");
        }else if(cardDetailsDTO.getCardNumber() > 13 && cardDetailsDTO.getCardNumber() < 20){
            throw new IllegalArgumentException("Number card is not valid!");
        }

        if(cardDetailsDTO.getExpirationDate() == null){
            throw new IllegalArgumentException("Expiration date is required!");
        }

        if(StringUtils.isEmpty(cardDetailsDTO.getNameCard())){
            throw new IllegalArgumentException("Name card is required!");
        }

        if(cardDetailsDTO.getCvv() == -1 ){
            throw new IllegalArgumentException("CVV is required!");
        }else if(cardDetailsDTO.getCvv() < 3 | cardDetailsDTO.getCvv() > 3){
            throw new IllegalArgumentException("CVV is not valid!");
        }
    }
}
