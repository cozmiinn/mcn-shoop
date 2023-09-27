package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String streetLine;
    private String postalCode;
    private String city;
    private String county;
    private String country;
}
