package com.insta.api.insta.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 35)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 35)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    @Email
    @Size(max = 40)
    private String email;

    @Size(max = 200)
    private String description;

    private String profilePhoto;

    // private List<Post> addedPosts;

    // private List<Follower> followers;

    // private List<Follower> following;
}
