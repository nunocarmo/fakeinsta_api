package com.insta.api.insta.command.follower;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowDto {
    @NotNull(message = "Follower id can't be empty")
    private Long followerUserId;

    @NotNull(message = "Id of user to follow can't be empty")
    private Long toFollowUserId;
}
