package com.insta.api.insta.command.follower;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
}
