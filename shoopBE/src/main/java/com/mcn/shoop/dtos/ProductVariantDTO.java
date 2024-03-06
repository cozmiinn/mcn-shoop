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
    private String pictureURL;
    private List<AttributeDTO> attribute;
}
