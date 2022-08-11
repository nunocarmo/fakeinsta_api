package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.converter.user.UserConverter;
import com.insta.api.insta.exception.*;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.insta.api.insta.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private UserConverter userConverter;

    @Override
    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        this.userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ConflictException(EMAIL_REGISTERED);
                });
        User user = this.userConverter.converter(userDto, User.class);
        //  user.setPassword(encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List <User> users = this.userRepository.findAll();
        return userConverter.converterList(users, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
       /* if (userUpdateDto.getPassword() != null) {
            userUpdateDto.setPassword(encoder.encode(userUpdateDto.getPassword()));
        }*/
        User updatedUser = this.userConverter.converterUpdate(userUpdateDto, user);
        return this.userConverter.converter(
                this.userRepository.save(updatedUser), UserDto.class);
    }

/*    @Override
    public List <UserDto> getUserByUsername(String username) {
       List <User> users = this.userRepository.findByUsername(username);
        return this.userConverter.converterList(users, UserDto.class);
    }*/

    @Override
    public UserDto getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.userConverter.converter(user, UserDto.class); }
}
