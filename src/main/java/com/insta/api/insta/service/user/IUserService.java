package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;

public interface IUserService {
    UserDto getUserById(Long userId);
}
