package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.entities.CartEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartEntryStructMapper {
    CartEntryDTO cartEntryToCartEntryDto(CartEntry cartEntry);
    CartEntry cartEntryDtoToCartEntry(CartEntryDTO cartEntryDTO);
}
