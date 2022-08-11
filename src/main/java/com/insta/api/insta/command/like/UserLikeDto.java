package com.insta.api.insta.command.like;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserLikeDto {
    private Long userId;
}
