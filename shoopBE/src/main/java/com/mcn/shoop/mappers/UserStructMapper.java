package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.UserDTO;
import com.mcn.shoop.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStructMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDTO);
}