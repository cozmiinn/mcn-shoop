package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductVariantDTO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private int availableQuantity;
    private Date addedDate;
    private List<AttributeDTO> attribute;
    private List<CartEntryDTO> cartEntries;
}
