package com.insta.api.insta.service.user;

import com.insta.api.insta.command.follower.AddFollowerDto;
import com.insta.api.insta.command.follower.FollowerDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.converter.user.IUserConverter;
import com.insta.api.insta.exception.*;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if(username.isEmpty() && email.isEmpty() && name.isEmpty()) {
            throw new BadRequestException(AT_LEAST_1_PARAMETER);
        }
        List<User> users = this.userRepository.findUsers(username, email, name);
        if(users.isEmpty()) throw new BadRequestException(USER_NOT_FOUND);
        return this.userConverter.converterList(users, UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public ResponseEntity followUser(AddFollowerDto addfollowerDto) {
        if(addfollowerDto.getToFollowUserId() == addfollowerDto.getFollowerUserId()) {
            throw new BadRequestException(CANNOT_FOLLOW_YOURSELF);
        }
        Long followerId = addfollowerDto.getFollowerUserId();
        Long userToFollowId = addfollowerDto.getToFollowUserId();

        User userFollower = this.userRepository.findById(followerId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User userToFollow = this.userRepository.findById(userToFollowId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if(!this.followerRepository.checkIfUserFollowsAlready(followerId, userToFollowId).isEmpty()) {
            throw new ConflictException(FOLLOW_ALREADY);
        }

        Follower follower = new Follower();
        follower.setFollowerUser(userFollower);
        follower.setFollowed(userToFollow);

        this.followerRepository.save(follower);

        this.userRepository.save(userFollower);
        this.userRepository.save(userToFollow);

        return new ResponseEntity<>("User with ID " + followerId + " started to follow user with ID " + userToFollowId, HttpStatus.OK);
    }

    @Override
    public UserDto unfollowUser(Long id, Long idToUnfollow) {
        return null;
    }
}
