package com.insta.api.insta.command.post;

import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.like.UserLikeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PostDto {
    private Long id;
    private String photo;
    private String description;
    private String creationDate;
    private Long userId;
    private List<UserLikeDto> postUserLikeList;
    private List<CommentDto> commentList;
}
