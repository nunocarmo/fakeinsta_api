package com.insta.api.insta.command.follower;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddFollowerDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id = null;
    @NotNull(message = "follower id can't be empty")
    private Long followerUserId;

    @NotNull(message = "Id of user to follow can't be empty")
    private Long toFollowUserId;
}
