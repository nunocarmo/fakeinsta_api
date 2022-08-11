package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.follower.FollowDto;

import java.util.List;

public interface IFollowService {
    List<FollowDto> getFollowsByUserId(Long id);


    FollowDto followUser(Long id, Long idToFollow);
}
