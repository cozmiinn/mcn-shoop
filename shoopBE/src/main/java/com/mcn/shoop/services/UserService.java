package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.mappers.AddressStructMapper;
import com.mcn.shoop.mappers.CardStructMapper;
import com.mcn.shoop.mappers.UserStructMapper;
import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.validators.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final CardDetailsService cardDetailsService;
    private final UserStructMapper userStructMapper;
    private final AddressStructMapper addressStructMapper;
    private final CardStructMapper cardStructMapper;


    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService, CardDetailsService cardDetailsService, UserStructMapper userStructMapper, AddressStructMapper addressStructMapper, CardStructMapper cardStructMapper) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.cardDetailsService = cardDetailsService;
        this.userStructMapper = userStructMapper;
        this.addressStructMapper = addressStructMapper;
        this.cardStructMapper = cardStructMapper;
    }


    public List<UserDTO> getUsers() {
        List<User> getUsers = userRepository.findAll();
        List<UserDTO> userDTOS = getUsers
                .stream()
                .map(userStructMapper::userToUserDto)
                .collect(Collectors.toList());
        return userDTOS;
    }

    public UserDTO getUser(Long id) {
        User getUser = userRepository.findById(id).orElseThrow(null);
        return userStructMapper.userToUserDto(getUser);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userStructMapper.userDtoToUser(userDTO);
        User create = userRepository.save(user);
        return userStructMapper.userToUserDto(create);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserValidator.validateUser(userDTO);

        User currentUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found!"));

        currentUser.setFirstName(userDTO.getFirstName());
        currentUser.setLastName(userDTO.getLastName());
        currentUser.setMiddleName(userDTO.getMiddleName());
        currentUser.setEmail(userDTO.getEmail());
        currentUser.setPhoneNumber(userDTO.getPhoneNumber());
        currentUser.setPassword(userDTO.getPassword());

        currentUser = userRepository.save(currentUser);

        return userStructMapper.userToUserDto(currentUser);
    }


    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public ResponseEntity<Object> addAddressToUser(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Address address = userStructMapper.addressDtoToAdress(addressDTO);
        List<Address> addresses = user.getAddress();
        addresses.add(address);
        user.setAddress(addresses);
        return new ResponseEntity<>("Address added to the user successfully", HttpStatus.OK);
    }
    }

//    public void addCardToUser(Long id, CardDetails cardDetails){
//        User user = userRepository.findById(id).orElse(null);
//        if(user != null){
//            cardDetails.setUser(user);
//            cardDetailsService.createCardDetails(cardDetails);
//        } else {
//            new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
//        }
//    }
}