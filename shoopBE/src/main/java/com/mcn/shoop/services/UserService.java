package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.mappers.AddressStructMapper;
import com.mcn.shoop.mappers.CardStructMapper;
import com.mcn.shoop.mappers.UserStructMapper;
import com.mcn.shoop.repositories.AddressRepository;
import com.mcn.shoop.repositories.CardDetailsRepository;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.validators.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStructMapper userStructMapper;
    private final AddressStructMapper addressStructMapper;
    private final CardStructMapper cardStructMapper;
    private final AddressRepository addressRepository;
    private final CardDetailsRepository cardDetailsRepository;


    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, UserStructMapper userStructMapper, AddressStructMapper addressStructMapper, CardStructMapper cardStructMapper, CardDetailsRepository cardDetailsRepository) {
        this.userRepository = userRepository;
        this.userStructMapper = userStructMapper;
        this.addressStructMapper = addressStructMapper;
        this.cardStructMapper = cardStructMapper;
        this.addressRepository = addressRepository;
        this.cardDetailsRepository = cardDetailsRepository;
    }


    public List<UserDTO> getUsers() {
        List<User> getUsers = userRepository.findAll();
        List<UserDTO> userDTOS;
        userDTOS = getUsers
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

    public AddressDTO addAddressToUser(@PathVariable("id") Long id, AddressDTO addressDTO) {
        User use = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            use = user.get();
        } else {
            new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        Address address = addressStructMapper.addressDtoToAddress(addressDTO);
        address.setUser(use);
        address = addressRepository.save(address);

        return addressStructMapper.addressToAddressDto(address);
    }

    public CardDetailsDTO addCardToUser(@PathVariable("id") Long id, CardDetailsDTO cardDetailsDTO){
        User use = null;
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            use = user.get();
        } else {
            new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        CardDetails cardDetails = cardStructMapper.cardDtoToCard(cardDetailsDTO);
        cardDetails.setUser(use);
        cardDetails = cardDetailsRepository.save(cardDetails);

        return cardStructMapper.cardToCardDto(cardDetails);
    }
}