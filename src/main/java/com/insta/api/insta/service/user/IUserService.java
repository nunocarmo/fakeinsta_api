package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;

import java.util.List;

public interface IUserService {
    UserDto getUserById(Long userId);

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

   // List <UserDto> getUserByUsername(String username);

    UserDto getUserByUsername(String username);
}
