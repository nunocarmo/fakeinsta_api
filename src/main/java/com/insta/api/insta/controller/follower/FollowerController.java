package com.insta.api.insta.controller.follower;

import com.insta.api.insta.command.follower.FollowerDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.service.follower.IFollowerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follower")
@AllArgsConstructor
public class FollowerController {

    private IFollowerService followerService;

    @GetMapping("/followers/{id}")
    public List<UserDto> getFollowersByUserId(@PathVariable("id") Long id) {
        return this.followerService.getFollowersByUserId(id);
    }

    @GetMapping("/follows/{id}")
    public List<UserDto> getFollowsByUserId(@PathVariable("id") Long id) {
        return this.followerService.getFollowsByUserId(id);
    }

}
