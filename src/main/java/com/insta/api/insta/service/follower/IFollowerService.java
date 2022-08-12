package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.user.UserDto;

import java.util.List;

public interface IFollowerService {
    List<UserDto> getFollowersByUserId(Long id);

    List<UserDto> getFollowsByUserId(Long id);

    List<UserDto> getFollowersOfLoggedUser();

    List<UserDto> getFollowsOfLoggedUser();
}
