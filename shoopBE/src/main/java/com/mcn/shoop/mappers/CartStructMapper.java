package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.CartDTO;
import com.mcn.shoop.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartStructMapper {
    CartDTO cartToCartDto(Cart cart);
    Cart cartDtoToCart(CartDTO cartDTO);
}
