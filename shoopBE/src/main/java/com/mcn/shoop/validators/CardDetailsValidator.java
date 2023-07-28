package com.mcn.shoop.validators;

import com.mcn.shoop.entities.CardDetails;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CardDetailsValidator {

    public static void validateCard(CardDetails cardDetails){
        if(cardDetails.getCardNumber() == -1){
            throw new IllegalArgumentException("Card details is required!");
        }else if(cardDetails.getCardNumber() > 13 && cardDetails.getCardNumber() < 20){
            throw new IllegalArgumentException("Number card is not valid!");
        }

        if(cardDetails.getExpirationDate() == null){
            throw new IllegalArgumentException("Expiration date is required!");
        }

        if(StringUtils.isEmpty(cardDetails.getNameCard())){
            throw new IllegalArgumentException("Name card is required!");
        }

        if(cardDetails.getCvv() == -1 ){
            throw new IllegalArgumentException("CVV is required!");
        }else if(cardDetails.getCvv() < 3 | cardDetails.getCvv() > 3){
            throw new IllegalArgumentException("CVV is not valid!");
        }
    }
}
