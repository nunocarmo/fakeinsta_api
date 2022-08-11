package com.insta.api.insta.service.user;

import com.insta.api.insta.command.follower.AddFollowerDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    UserDto getUserById(Long userId);

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    List <UserDto> searchUsers(String username, String email, String name);

    UserDto getUserByUsername(String username);

    

    UserDto unfollowUser(Long id, Long idToUnfollow);

    ResponseEntity followUser(AddFollowerDto addFollowerDto);
}
