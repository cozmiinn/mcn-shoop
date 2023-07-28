package com.mcn.shoop.validators;

import com.mcn.shoop.entities.Address;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator {
    public static void validateAddress(Address address){
        if(StringUtils.isEmpty(address.getStreetLine())){
            throw new IllegalArgumentException("Street name is required!");
        }

        if(StringUtils.isEmpty(address.getPostalCode())){
            throw new IllegalArgumentException("Postal code is required!");
        }

        if(StringUtils.isEmpty(address.getCity())){
            throw new IllegalArgumentException("City name is required!");
        }

        if(StringUtils.isEmpty(address.getCounty())){
            throw new IllegalArgumentException("County region is required!");
        }

        if(StringUtils.isEmpty(address.getCountry())){
            throw new IllegalArgumentException("Country name is required!");
        }
    }
}