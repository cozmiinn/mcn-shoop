package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.UserDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {
    public static void validateUser(UserDTO userDTO){
//        if(StringUtils.isEmpty(userDTO.getFirstName())){
//            throw new IllegalArgumentException("The first name is required!");
//        }

        if(StringUtils.isEmpty(userDTO.getLastName())){
            throw new IllegalArgumentException("The last name is required!");
        }

        if(StringUtils.isEmpty(userDTO.getEmail())){
            throw new IllegalArgumentException("Email is required!");
        }

        if(StringUtils.isEmpty(userDTO.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number is required!");
        }

        if(StringUtils.isEmpty(userDTO.getPassword())){
            throw new IllegalArgumentException("Password is required!");
        }

    }

}
