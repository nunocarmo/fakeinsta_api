package com.insta.api.insta.controller.user;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.command.follower.UnfollowDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping()
    @Cacheable(value = "users")
    public UserDto getUserById() {
        return this.userService.getUserById();
    }

    @GetMapping("/search")
    public List <UserDto> searchUsers(@RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "name", required = false) String name) {
        return this.userService.searchUsers(username, email, name);
    }
    @GetMapping("/admin")
    @Cacheable(value = "users")
    @CachePut(value="users")
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        return this.userService.registerUser(userDto);
    }

    @PatchMapping()
    public UserDto updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        return this.userService.updateUser(userUpdateDto);
    }

    @PatchMapping("/follow")
    public ResponseEntity followUser(@RequestBody FollowDto followDto) {
        return this.userService.followUser(followDto);
    }

    @PatchMapping("/unfollow")
    public ResponseEntity unfollowUser(@RequestBody UnfollowDto unfollowDto) {
        return this.userService.unfollowUser(unfollowDto);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        return this.userService.deleteUser(id);
    }

    @DeleteMapping()
    public ResponseEntity deleteUser() {
        return this.userService.deleteUser();
    }


}
