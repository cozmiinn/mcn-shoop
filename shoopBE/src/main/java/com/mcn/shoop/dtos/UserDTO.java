package com.mcn.shoop.dtos;

//import com.mcn.shoop.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDTO{
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String password;
//    private UserRole role;
    private List<AddressDTO> address;
    private List<CardDetailsDTO> paymentCards;

}
