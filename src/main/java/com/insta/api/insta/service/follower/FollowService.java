package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.converter.follower.IFollowerConverter;
import com.insta.api.insta.persistence.model.follower.Follow;
import com.insta.api.insta.persistence.repository.follower.IFollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowService implements IFollowService {

    private IFollowRepository followRepository;
    private IFollowerConverter followConverter;

    @Override
    public List<FollowDto> getFollowsByUserId(Long id) {
        List <Follow> followUsers = this.followRepository.findFollowsByUserId(id);
        return this.followConverter.converterList(followUsers, FollowDto.class);
    }

    @Override
    public FollowDto followUser(Long id, Long idToFollow) {
        return null;
    }

}
