package com.insta.api.insta.service.user;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.command.follower.UnfollowDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.converter.user.IUserConverter;
import com.insta.api.insta.exception.*;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import com.insta.api.insta.security.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.insta.api.insta.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IUserConverter userConverter;

    private IFollowerRepository followerRepository;
    private final LoggedUser users;
    @Override
    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(users.getLoggedUser().getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        this.userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ConflictException(EMAIL_REGISTERED);
                });

        this.userRepository.findByUsername(userDto.getUsername())
                .ifPresent(user -> {
                    throw new ConflictException(USERNAME_REGISTERED);
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

      this.userRepository.findByEmail(userUpdateDto.getEmail()).ifPresent(
              userEmail -> {
                  if(userEmail != user) {
                      throw new BadRequestException(EMAIL_REGISTERED);
                  }
              }
      );
        this.userRepository.findByUsername(userUpdateDto.getUsername()).ifPresent(
                userUsername -> {
                    if(userUsername != user) {
                        throw new BadRequestException(USERNAME_REGISTERED);
                    }
                }
        );

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
    public ResponseEntity followUser(FollowDto followDto) {
        if(followDto.getToFollowUserId() == followDto.getFollowerUserId()) {
            throw new BadRequestException(CANNOT_FOLLOW_YOURSELF);
        }
        Long followerId = followDto.getFollowerUserId();
        Long userToFollowId = followDto.getToFollowUserId();

        User userFollower = this.userRepository.findById(followerId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User userToFollow = this.userRepository.findById(userToFollowId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if(!this.followerRepository.checkIfUserFollowsAlready(followerId, userToFollowId).isEmpty()) {
            throw new ConflictException(FOLLOW_ALREADY);
        }
        Follower follower = new Follower();
        follower.setFollowerUser(userFollower);
        userFollower.getFollows().add(follower);

        follower.setFollowed(userToFollow);
        userToFollow.getFollowers().add(follower);

        this.followerRepository.save(follower);
        this.userRepository.save(userFollower);
        this.userRepository.save(userToFollow);

        return new ResponseEntity<>("User with ID " + followerId + " started to follow user with ID " + userToFollowId, HttpStatus.OK);
    }

    @Override
    public ResponseEntity unfollowUser(UnfollowDto unfollowDto) {
        if(unfollowDto.getToUnfollowUserId() == unfollowDto.getFollowerUserId()) {
            throw new BadRequestException(CANNOT_UNFOLLOW_YOURSELF);
        }
        Long followerId = unfollowDto.getFollowerUserId();
        Long userToUnfollowId = unfollowDto.getToUnfollowUserId();

        User userFollower = this.userRepository.findById(followerId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User userToUnfollow = this.userRepository.findById(userToUnfollowId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        Follower follower = this.followerRepository.findFollowerAndFollowedMatch(followerId, userToUnfollowId)
                .orElseThrow(() -> new ConflictException(HAVE_NOT_FOLLOWED));

        userFollower.getFollows().remove(follower);
        userToUnfollow.getFollowers().remove(follower);

        this.followerRepository.delete(follower);
        this.userRepository.save(userFollower);
        this.userRepository.save(userToUnfollow);

        return new ResponseEntity<>("User with ID " + followerId + " unfollowed user with ID " + userToUnfollowId, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        //checkAuth.checkIfUserEqualsIdGiven(id);
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NOT_FOUND);

        //clearUserCache();
        this.userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }


}
