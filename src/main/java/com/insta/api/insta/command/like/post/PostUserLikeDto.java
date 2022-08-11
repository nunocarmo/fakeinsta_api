package com.insta.api.insta.command.like.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PostUserLikeDto {
    private Long id;
    private Long postId;
    private Long userId;
}
