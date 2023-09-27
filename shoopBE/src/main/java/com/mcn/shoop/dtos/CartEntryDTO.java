package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartEntryDTO {
    private Long id;
    private int quantity;
    private double pricePerPiece;
    private double totalPricePerEntry;
}
