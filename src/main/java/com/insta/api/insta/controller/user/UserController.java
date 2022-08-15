package com.insta.api.insta.controller.user;

import com.insta.api.insta.command.follower.FollowDto;
import com.insta.api.insta.command.follower.UnfollowDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private IUserService userService;

    @GetMapping()
    public UserDto getLoggedUser() {
        return this.userService.getLoggedUser();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/search")
    public List <UserDto> searchUsers(@RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "name", required = false) String name) {
        return this.userService.searchUsers(username, email, name);
    }
    @GetMapping("/admin")
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
