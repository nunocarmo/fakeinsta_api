package com.insta.api.insta.controller.follower;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.service.follower.IFollowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/following")
@AllArgsConstructor
public class FollowController {
    private IFollowService followService;


    @GetMapping("/{id}")
    public List<FollowDto> getFollowsByUserId(@PathVariable("id") Long id) {
        return this.followService.getFollowsByUserId(id);
    }


}
