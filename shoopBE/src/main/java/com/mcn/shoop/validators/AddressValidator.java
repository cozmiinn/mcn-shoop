package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.entities.Address;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator {
    public static void validateAddress(AddressDTO addressDTO){
        if(StringUtils.isEmpty(addressDTO.getStreetLine())){
            throw new IllegalArgumentException("Street name is required!");
        }

        if(StringUtils.isEmpty(addressDTO.getPostalCode())){
            throw new IllegalArgumentException("Postal code is required!");
        }

        if(StringUtils.isEmpty(addressDTO.getCity())){
            throw new IllegalArgumentException("City name is required!");
        }

        if(StringUtils.isEmpty(addressDTO.getCounty())){
            throw new IllegalArgumentException("County region is required!");
        }

        if(StringUtils.isEmpty(addressDTO.getCountry())){
            throw new IllegalArgumentException("Country name is required!");
        }
    }
}