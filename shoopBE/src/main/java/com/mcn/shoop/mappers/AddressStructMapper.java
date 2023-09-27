package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressStructMapper {
    AddressDTO addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDTO addressDTO);
}
