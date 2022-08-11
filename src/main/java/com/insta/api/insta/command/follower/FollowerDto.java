package com.insta.api.insta.command.follower;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    private UserDto followerUser;

    private UserDto followed;

}
