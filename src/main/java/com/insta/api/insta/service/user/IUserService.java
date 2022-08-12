package com.insta.api.insta.service.user;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.command.follower.UnfollowDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    UserDto getUserById();

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserUpdateDto userUpdateDto);

    List <UserDto> searchUsers(String username, String email, String name);

    UserDto getUserByUsername(String username);

    ResponseEntity followUser(FollowDto followDto);

    ResponseEntity unfollowUser(UnfollowDto unfollowDto);

    ResponseEntity deleteUser(Long id);

    ResponseEntity deleteUser();
}
