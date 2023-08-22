package com.mcn.shoop.services;

import com.mcn.shoop.entities.Address;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final AddressService addressService;

    private final CardDetailsService cardDetailsService;


    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService, CardDetailsService cardDetailsService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.cardDetailsService = cardDetailsService;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id,User user) {
        UserValidator.validateUser(user);
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setMiddleName(user.getMiddleName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setPassword(user.getPassword());

        return userRepository.save(currentUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void addAddressToUser(Long id, Address address){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            address.setUser(user);
            addressService.createAddress(address);
        } else {
            new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
    }

    public void addCardToUser(Long id, CardDetails cardDetails){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            cardDetails.setUser(user);
            cardDetailsService.createCardDetails(cardDetails);
        } else {
            new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
        }
    }
}
