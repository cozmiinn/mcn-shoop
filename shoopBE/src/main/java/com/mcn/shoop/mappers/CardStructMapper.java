package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.CardDetailsDTO;
import com.mcn.shoop.entities.CardDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardStructMapper {
    CardDetailsDTO cardToCardDto (CardDetails cardDetails);
    CardDetails cardDtoToCard (CardDetailsDTO cardDetailsDTO);

}
