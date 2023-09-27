package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseProductDTO {
    private Long id;
    private String type;
    private List<ProductVariantDTO> productVariants;

}
