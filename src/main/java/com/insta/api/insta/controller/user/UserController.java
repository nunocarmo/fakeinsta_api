package com.insta.api.insta.controller.user;

import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.service.user.IUserService;
import com.insta.api.insta.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/user")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(Long id) {
        return this.userService.getUserById(id);
    }

   /* GET getUser(userId)
    GET getUserByUsername(String username)
    GET getAllFollowers(userId)
    GET getAllFollows(userId)
    POST register(request body)
    Login JWT
    PATCH updateUserDetails(request body): para o password, username, email
    PATCH updateUserProfile(request body): para descrição, nome completo, foto
    POST/ PATCH follow(userId, otherUserId)
    POST/ PATCH unfollow(userId, otherUserId)
    DELETE deleteUser(userId)*/

}
