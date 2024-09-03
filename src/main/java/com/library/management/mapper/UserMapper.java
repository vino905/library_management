package com.library.management.mapper;

import com.library.management.model.dto.UserDto;
import com.library.management.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder().email(user.getEmail()).name(user.getName()).id(user.getId()).build();
    }

    public User toEntity(UserDto userDto) {
        return User.builder().name(userDto.getName()).email(userDto.getEmail()).build();
    }
}
