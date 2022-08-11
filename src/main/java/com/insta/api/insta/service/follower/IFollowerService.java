package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.follower.FollowerDto;

import java.util.List;

public interface IFollowerService {
    List<FollowerDto> getFollowersByUserId(Long id);
}
