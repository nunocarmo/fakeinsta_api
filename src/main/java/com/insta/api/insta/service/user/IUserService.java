package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;

import java.util.List;

public interface IUserService {
    UserDto getUserById(Long userId);

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
