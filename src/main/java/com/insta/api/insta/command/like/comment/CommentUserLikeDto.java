package com.insta.api.insta.command.like.comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CommentUserLikeDto {
    private Long id;
    private Long commentId;
    private Long userId;
}
