package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.follower.FollowerDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.converter.follower.IFollowerConverter;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.security.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FollowerService implements IFollowerService {

    private IFollowerRepository followerRepository;
    private IFollowerConverter followerConverter;

    private final LoggedUser loggedUser;

    @Override
    public List<UserDto> getFollowersByUserId(Long id) {
        List <Follower> followersList = this.followerRepository.findFollowersByUserId(id);
        List <User> followers = followersList.stream()
                .map(follower ->
                        follower.getFollowerUser()).collect(Collectors.toList());

        return this.followerConverter.converterList(followers, UserDto.class);
    }

    @Override
    public List<UserDto> getFollowersOfLoggedUser() {
        List <Follower> followersList = this.followerRepository.findFollowersByUserId(loggedUser.getLoggedUser().getId());
        List <User> followers = followersList.stream()
                .map(follower ->
                        follower.getFollowerUser()).collect(Collectors.toList());

        return this.followerConverter.converterList(followers, UserDto.class);
    }

    @Override
    public List<UserDto> getFollowsOfLoggedUser() {
        List <Follower> follows = this.followerRepository.findFollowsByUserId(loggedUser.getLoggedUser().getId());
        List <User> followedUsers = follows.stream()
                .map(follow ->
                        follow.getFollowed()).collect(Collectors.toList());
        return this.followerConverter.converterList(followedUsers, UserDto.class);
    }

    @Override
    public List<UserDto> getFollowsByUserId(Long id) {
        List <Follower> follows = this.followerRepository.findFollowsByUserId(id);
        List <User> followedUsers = follows.stream()
                .map(follow ->
                    follow.getFollowed()).collect(Collectors.toList());
        return this.followerConverter.converterList(followedUsers, UserDto.class);
    }


}
