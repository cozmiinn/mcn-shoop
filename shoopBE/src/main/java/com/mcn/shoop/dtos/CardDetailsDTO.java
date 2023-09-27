package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardDetailsDTO {
    private Long id;
    private Long cardNumber;
    private Date expirationDate;
    private String nameCard;
    private int cvv;
}
