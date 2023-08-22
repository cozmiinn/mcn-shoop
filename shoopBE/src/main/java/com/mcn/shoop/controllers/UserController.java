package com.mcn.shoop.controllers;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.entities.CardDetails;
import com.mcn.shoop.entities.Cart;
import com.mcn.shoop.entities.User;
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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
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
        userService.addAddressToUser(id, address);
        return new ResponseEntity<>("Address added to the user successfully", HttpStatus.OK);
    }


    @PostMapping("/{id}/cards")
    public ResponseEntity<Object> addCardToUser(@PathVariable("id") Long id, @RequestBody CardDetails cardDetails){
        userService.addCardToUser(id, cardDetails);
        return new ResponseEntity<>("Payment card added to user successfully!", HttpStatus.OK);
    }

}