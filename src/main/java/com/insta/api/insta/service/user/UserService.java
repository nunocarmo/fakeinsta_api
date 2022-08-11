package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.converter.user.UserConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.user.IUserRepository;

import static com.insta.api.insta.exception.ExceptionMessages.*;

public class UserService implements IUserService {

    private IUserRepository userRepository;
    private UserConverter userConverter;
    @Override
    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return null;
    }
}
