package com.mcn.shoop.services;

import com.mcn.shoop.entities.Cart;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.entities.Voucher;
import com.mcn.shoop.repositories.CartEntryRepository;
import com.mcn.shoop.repositories.CartRepository;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.repositories.VouchersRepository;
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

    private final CartEntryRepository cartEntryRepository;

    private final VouchersRepository vouchersRepository;

    private final VouchersService vouchersService;

    @Autowired
    public CartService(CartRepository cartRepository, CartEntryService cartEntryService, UserService userService, UserRepository userRepository, CartEntryRepository cartEntryRepository, VouchersRepository vouchersRepository, VouchersService vouchersService) {
        this.cartRepository = cartRepository;
        this.cartEntryService = cartEntryService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.vouchersRepository = vouchersRepository;
        this.vouchersService = vouchersService;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart cart) {
        Cart currentCart = cartRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCart.setTotalPrice(cart.getTotalPrice());

        return cartRepository.save(currentCart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void addEntryToCart(Long id, CartEntry cartEntry) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cartEntry.setCart(cart);
            cartEntryService.createCartEntry(cartEntry);
        }
    }

    public ResponseEntity<Object> addCartToUser(Long id, Long userId) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }

        User users = userRepository.findById(userId).orElse(null);
        if (users == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        if (cart.getUser().contains(users)) {
            return new ResponseEntity<>("Cart already exists!", HttpStatus.BAD_REQUEST);
        }

        cart.getUser().add(users);
        users.setCart(cart);
        userService.createUser(users);

        return new ResponseEntity<>("Cart added to the user successfully!", HttpStatus.OK);
    }

    public ResponseEntity<Object> addEntryToCart(Long id, Long entryId) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }

        CartEntry cartEntry = cartEntryRepository.findById(entryId).orElse(null);
        if (cartEntry == null) {
            return new ResponseEntity<>("Entry not found!", HttpStatus.NOT_FOUND);
        }

        if (cart.getCartEntries().contains(cartEntry)) {
            return new ResponseEntity<>("Entry already exists!", HttpStatus.BAD_REQUEST);
        }

        cart.getCartEntries().add(cartEntry);
        cartEntry.setCart(cart);
        cartEntryService.createCartEntry(cartEntry);

        return new ResponseEntity<>("Entry added to the cart successfully!", HttpStatus.OK);
    }

    public ResponseEntity<Object> addVoucherToCart(Long id, Long voucherId){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }

        Voucher voucher = vouchersRepository.findById(voucherId).orElse(null);
        if(voucher == null){
            return new ResponseEntity<>("Voucher not found!", HttpStatus.NOT_FOUND);
        }

        if(cart.getVouchers().contains(voucher)){
            return new ResponseEntity<>("Voucher already exists!", HttpStatus.BAD_REQUEST);
        }

        cart.getVouchers().add(voucher);
        voucher.setCart(cart);
        vouchersService.createVouchers(voucher);

        return new ResponseEntity<>("Voucher added to the cart successfully", HttpStatus.OK);
    }
}

