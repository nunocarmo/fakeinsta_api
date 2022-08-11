package com.insta.api.insta.service.follower;

import com.insta.api.insta.command.follower.FollowerDto;
import com.insta.api.insta.converter.follower.IFollowerConverter;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowerService implements IFollowerService {

    private IFollowerRepository followerRepository;
    private IFollowerConverter followerConverter;

    @Override
    public List<FollowerDto> getFollowersByUserId(Long id) {
        List <Follower> followers = this.followerRepository.findFollowersByUserId(id);
        return this.followerConverter.converterList(followers, FollowerDto.class);
    }
}
