package com.insta.api.insta.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.command.comment.CommentIdDto;
import com.insta.api.insta.command.post.PostIdDto;
import com.insta.api.insta.command.post.PostLessInfDto;
import com.insta.api.insta.persistence.model.follower.Follower;
import lombok.*;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Size(max = 200)
    private String description = "Hello! Is it me you're looking for?";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String profilePhoto = "https://freesvg.org/img/red_avatar.png";

    private List<CommentIdDto> commentUserLikeList;
    private List<PostIdDto> postUserLikeList;
    private List<PostLessInfDto> postList;

    private List<Follower> followers;

    private List<Follower> following;
}
