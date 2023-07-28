package com.mcn.shoop.controllers;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.services.AddressService;
import com.mcn.shoop.services.CardDetailsService;
import com.mcn.shoop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;
    private final AddressService addressService;
    private final CardDetailsService cardDetailsService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, AddressService addressService, CardDetailsService cardDetailsService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.cardDetailsService = cardDetailsService;
    }

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }



    @PostMapping("/{id}/address")
    public ResponseEntity<Object> addAddressToUser(@PathVariable("id") Long id, @RequestBody Address address) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        address.setUser(user);
        addressService.createAddress(address);
        return new ResponseEntity<>("Address added to the user successfully", HttpStatus.OK);
    }


    @PostMapping("/{id}/cards")
    public ResponseEntity<Object> addCardToUser(@PathVariable("id") Long id, @RequestBody CardDetails cardDetails){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        cardDetails.setUser(user);
        cardDetailsService.createCardDetails(cardDetails);
        return new ResponseEntity<>("Payment card added to the user successfully", HttpStatus.OK);
    }

}