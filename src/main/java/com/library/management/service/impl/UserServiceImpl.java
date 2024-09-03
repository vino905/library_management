package com.library.management.service.impl;

import com.library.management.mapper.UserMapper;
import com.library.management.model.dto.UserDto;
import com.library.management.model.entity.User;
import com.library.management.repository.UserRepository;
import com.library.management.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        log.info("saving user details in db : userDetails : {}", user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
