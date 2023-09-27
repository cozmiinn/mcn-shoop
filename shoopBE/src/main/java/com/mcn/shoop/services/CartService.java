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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final CartEntryService cartEntryService;

    private final UserService userService;

    private final UserRepository userRepository;

    private final CartEntryRepository cartEntryRepository;
    private final VouchersRepository vouchersRepository;
    private final VouchersService vouchersService;
    private final VoucherStructMapper voucherStructMapper;
    private final CartEntryStructMapper cartEntryStructMapper;
    private final UserStructMapper userStructMapper;
    private final CartStructMapper cartStructMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CartEntryService cartEntryService, UserService userService, UserRepository userRepository, CartEntryRepository cartEntryRepository, VouchersRepository vouchersRepository, VouchersService vouchersService, VoucherStructMapper voucherStructMapper, CartEntryStructMapper cartEntryStructMapper, UserStructMapper userStructMapper, CartStructMapper cartStructMapper) {
        this.cartRepository = cartRepository;
        this.cartEntryService = cartEntryService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.vouchersRepository = vouchersRepository;
        this.vouchersService = vouchersService;
        this.voucherStructMapper = voucherStructMapper;
        this.cartEntryStructMapper = cartEntryStructMapper;
        this.userStructMapper = userStructMapper;
        this.cartStructMapper = cartStructMapper;
    }

    public List<CartDTO> getCarts() {
        List<Cart> getCarts = cartRepository.findAll();
        List<CartDTO> cartDTOS = getCarts
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



    public ResponseEntity<String> addEntryToCart(Long id, CartEntryDTO cartEntryDTO) {
        Cart cart = cartRepository.findById(id).orElse(null);
        CartEntry entry = cartEntryStructMapper.cartEntryDtoToCartEntry(cartEntryDTO);
        if (cart != null) {
            entry.setCart(cart);
            cartEntryService.createCartEntry(cartEntryDTO);
        }else {
            new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        cartEntryStructMapper.cartEntryToCartEntryDto(entry);
        return new ResponseEntity<>("Entity added to the cart successfully!", HttpStatus.OK);
    }



    public ResponseEntity<Object> addCartToUser(Long id, Long userId, UserDTO userDTO) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        User users = userRepository.findById(userId).orElse(null);
        User user = userStructMapper.userDtoToUser(userDTO);
        if (users == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
        if (cart.getUser().contains(users)) {
            return new ResponseEntity<>("Cart already exists!", HttpStatus.BAD_REQUEST);
        }
        cart.getUser().add(users);
        users.setCart(cart);
        userService.createUser(userDTO);
        userStructMapper.userToUserDto(user);
        return new ResponseEntity<>("Cart added to the user successfully!", HttpStatus.OK);
    }



    public ResponseEntity<Object> addEntryToCart(Long id, Long entryId, CartEntryDTO cartEntryDTO) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        CartEntry cartEntry = cartEntryRepository.findById(entryId).orElse(null);
        CartEntry cartEntry1 = cartEntryStructMapper.cartEntryDtoToCartEntry(cartEntryDTO);
        if (cartEntry == null) {
            return new ResponseEntity<>("Entry not found!", HttpStatus.NOT_FOUND);
        }
        if (cart.getCartEntries().contains(cartEntry)) {
            return new ResponseEntity<>("Entry already exists!", HttpStatus.BAD_REQUEST);
        }
        cart.getCartEntries().add(cartEntry);
        cartEntry.setCart(cart);
        cartEntryStructMapper.cartEntryToCartEntryDto(cartEntry1);
        cartEntryService.createCartEntry(cartEntryDTO);
        return new ResponseEntity<>("Entry added to the cart successfully!", HttpStatus.OK);
    }



    public ResponseEntity<String> addVoucherToCart(Long id, Long voucherId, VoucherDTO voucherDTO){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return new ResponseEntity<>("Cart not found!", HttpStatus.NOT_FOUND);
        }
        Voucher voucher = vouchersRepository.findById(voucherId).orElse(null);
        Voucher voucher1 = voucherStructMapper.voucherDtoToVoucher(voucherDTO);
        if(voucher == null){
            return new ResponseEntity<>("Voucher not found!", HttpStatus.NOT_FOUND);
        }
        if(cart.getVouchers().contains(voucher)){
            return new ResponseEntity<>("Voucher already exists!", HttpStatus.BAD_REQUEST);
        }
        cart.getVouchers().add(voucher);
        voucher.setCart(cart);
        voucherStructMapper.voucherToVoucherDto(voucher1);
        vouchersService.createVouchers(voucherDTO);
        return new ResponseEntity<>("Voucher added to the cart successfully", HttpStatus.OK);
    }
}

