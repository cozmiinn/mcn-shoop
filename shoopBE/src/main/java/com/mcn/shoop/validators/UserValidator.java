package com.mcn.shoop.validators;

import com.mcn.shoop.entities.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {
    public static void validateUser(User user){
        if(StringUtils.isEmpty(user.getFirstName())){
            throw new IllegalArgumentException("The first name is required!");
        }

        if(StringUtils.isEmpty(user.getLastName())){
            throw new IllegalArgumentException("The last name is required!");
        }

        if(StringUtils.isEmpty(user.getSecondName())){
            throw new IllegalArgumentException("The middle is required.!");
        }

        if(StringUtils.isEmpty(user.getEmail())){
            throw new IllegalArgumentException("Email is required!");
        }

        if(StringUtils.isEmpty(user.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number is required!");
        }

        if(StringUtils.isEmpty(user.getPassword())){
            throw new IllegalArgumentException("Password is required!");
        }

    }

}
