package com.mcn.shoop.services;

import com.mcn.shoop.dtos.CartDTO;
import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.dtos.VoucherDTO;
import com.mcn.shoop.entities.Cart;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.User;
import com.mcn.shoop.entities.Voucher;
import com.mcn.shoop.mappers.CartEntryStructMapper;
import com.mcn.shoop.mappers.CartStructMapper;
import com.mcn.shoop.mappers.UserStructMapper;
import com.mcn.shoop.mappers.VoucherStructMapper;
import com.mcn.shoop.repositories.CartEntryRepository;
import com.mcn.shoop.repositories.CartRepository;
import com.mcn.shoop.repositories.UserRepository;
import com.mcn.shoop.repositories.VouchersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartEntryRepository cartEntryRepository;
    private final VouchersRepository vouchersRepository;
    private final VoucherStructMapper voucherStructMapper;
    private final CartEntryStructMapper cartEntryStructMapper;
    private final UserStructMapper userStructMapper;
    private final CartStructMapper cartStructMapper;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, CartEntryRepository cartEntryRepository, VouchersRepository vouchersRepository, VoucherStructMapper voucherStructMapper, CartEntryStructMapper cartEntryStructMapper, UserStructMapper userStructMapper, CartStructMapper cartStructMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.vouchersRepository = vouchersRepository;
        this.voucherStructMapper = voucherStructMapper;
        this.cartEntryStructMapper = cartEntryStructMapper;
        this.userStructMapper = userStructMapper;
        this.cartStructMapper = cartStructMapper;
    }

    public List<CartDTO> getCarts() {
        List<Cart> getCarts = cartRepository.findAll();
        List<CartDTO> cartDTOS;
        cartDTOS = getCarts
                .stream()
                .map(cartStructMapper::cartToCartDto)
                .collect(Collectors.toList());
        return cartDTOS;
    }

    public CartDTO getCart(Long id) {
        Cart getCart = cartRepository.findById(id).orElse(null);
        return cartStructMapper.cartToCartDto(getCart);
    }

    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = cartStructMapper.cartDtoToCart(cartDTO);
        Cart create = cartRepository.save(cart);
        return cartStructMapper.cartToCartDto(create);
    }

    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Cart currentCart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart with id " + id + " not found!"));
        currentCart.setTotalPrice(cartDTO.getTotalPrice());

        currentCart = cartRepository.save(currentCart);

        return cartStructMapper.cartToCartDto(currentCart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public UserDTO addCartToUser(@PathVariable("id") Long cartId, @PathVariable("id") Long userId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        if (cart != null && cart.getUser().contains(user)) {
            new ResponseEntity<>("Cart already exists!", HttpStatus.BAD_REQUEST);
        }
        user.setCart(cart);
        user = userRepository.save(user);

        return userStructMapper.userToUserDto(user);
    }


    public CartEntryDTO addEntryToCart(@PathVariable("id") Long cartId, @PathVariable("id") Long entryId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        CartEntry cartEntry = cartEntryRepository.findById(entryId).orElse(null);
        if (cartEntry == null) {
            new ResponseEntity<>("Entry not found!", HttpStatus.NOT_FOUND);
        }

        if (cart != null && cart.getCartEntries() != null && cart.getCartEntries().contains(cartEntry)) {
            new ResponseEntity<>("Entry already exists!", HttpStatus.BAD_REQUEST);
        }
        cartEntry.setCart(cart);
        cartEntry = cartEntryRepository.save(cartEntry);

        return cartEntryStructMapper.cartEntryToCartEntryDto(cartEntry);
    }


    public VoucherDTO addVoucherToCart(@PathVariable("cartId") Long cartId, @PathVariable("id") Long voucherId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }

        Voucher voucher = vouchersRepository.findById(voucherId).orElse(null);
        if (voucher == null) {
            new ResponseEntity<>("Voucher not found!", HttpStatus.NOT_FOUND);
        }

        if (cart != null && cart.getVouchers() != null && cart.getVouchers().contains(voucher)) {
            new ResponseEntity<>("Voucher already exists!", HttpStatus.BAD_REQUEST);
        }
        voucher.setCart(cart);
        voucher = vouchersRepository.save(voucher);

        return voucherStructMapper.voucherToVoucherDto(voucher);
    }
}
