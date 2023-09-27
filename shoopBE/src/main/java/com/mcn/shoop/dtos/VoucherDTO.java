package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherDTO {
    private Long id;
    private String cod;
    private String clientName;
    private Float discount;
}
