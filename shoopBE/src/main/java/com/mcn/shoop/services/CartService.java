package com.mcn.shoop.services;

import com.mcn.shoop.entities.Cart;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.repositories.CartRepository;
import com.mcn.shoop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final CartEntryService cartEntryService;

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartEntryService cartEntryService, UserService userService, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartEntryService = cartEntryService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<Cart> getCarts(){
        return cartRepository.findAll();
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart cart){
        Cart currentCart = cartRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCart.setTotalPrice(cart.getTotalPrice());

        return cartRepository.save(currentCart);
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }

    public void addEntryToCart(Long id, CartEntry cartEntry){
        Cart cart = cartRepository.findById(id).orElse(null);
        if(cart != null){
            cartEntry.setCart(cart);
            cartEntryService.createCartEntry(cartEntry);
        }
    }

    public ResponseEntity<Object> addCartToUser(Long id, Long userId) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
        }

        User users = userRepository.findById(userId).orElse(null);
        if (users == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if(cart.getUser().contains(users)){
            return new ResponseEntity<>("Cart already exists!", HttpStatus.BAD_REQUEST);
        }

        cart.getUser().add(users);
        users.setCart(cart);
        userService.createUser(users);

        return new ResponseEntity<>("Cart added to the user successfully", HttpStatus.OK);
    }


}

