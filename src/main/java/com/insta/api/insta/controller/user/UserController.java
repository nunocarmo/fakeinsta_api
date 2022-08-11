package com.insta.api.insta.controller.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.command.user.UserUpdateDto;
import com.insta.api.insta.service.user.IUserService;
import com.insta.api.insta.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

/*    @GetMapping("/search/{username}")
    public List <UserDto> getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }*/

    @GetMapping("/search/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);}

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public UserDto registerUser(@RequestBody UserDto userDto) {
        return this.userService.registerUser(userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto userUpdateDto) {
        return this.userService.updateUser(id, userUpdateDto);
    }

   /*
    GET getUserByUsername(String username)
    GET getAllFollowers(userId)
    GET getAllFollows(userId)
    Login JWT
   DONE PATCH updateUserDetails(request body): para o password, username, email
   DONE PATCH updateUserProfile(request body): para descrição, nome completo, foto
    POST/ PATCH follow(userId, otherUserId)
    POST/ PATCH unfollow(userId, otherUserId)
    DELETE deleteUser(userId)*/

}
