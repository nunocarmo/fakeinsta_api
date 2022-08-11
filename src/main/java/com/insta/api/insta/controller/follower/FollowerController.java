package com.insta.api.insta.controller.follower;

import com.insta.api.insta.command.follower.FollowerDto;
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

    @GetMapping("/{id}")
    public List<FollowerDto> getFollowersByUserId(@PathVariable("id") Long id) {
        return this.followerService.getFollowersByUserId(id);
    }
}
