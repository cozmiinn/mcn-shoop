package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartEntryDTO {
    private Long id;
    private ProductVariantDTO variants;
    private int quantity;
    private double pricePerPiece;
    private double totalPricePerEntry;
}
