package com.insta.api.insta.service.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.converter.user.IUserConverter;
import com.insta.api.insta.exception.*;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.insta.api.insta.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IUserConverter userConverter;

    private IFollowerRepository followerRepository;

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
        List<User> users = this.userRepository.findAll();
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

    @Override
    public List<UserDto> searchUsers(String username, String email, String name) {
        List<User> users = this.userRepository.findUsers(username, email, name);
        return this.userConverter.converterList(users, UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public UserDto followUser(Long id, Long idToFollow) {
        if(id == idToFollow) {
            throw new BadRequestException(CANNOT_FOLLOW_YOURSELF);
        }
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User userToFollow = this.userRepository.findById(idToFollow)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if(!this.followerRepository.checkIfUserFollowsAlready(id, idToFollow).isEmpty()) {
            throw new ConflictException(FOLLOW_ALREADY);
        }
        Follower follower = new Follower();
        follower.setFollowed(userToFollow);
        follower.setFollowerUser(user);
        this.followerRepository.save(follower);

        this.userRepository.save(user);
        this.userRepository.save(userToFollow);
        return this.userConverter.converter(user, UserDto.class);
    }
}
