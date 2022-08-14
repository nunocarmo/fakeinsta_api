package com.insta.api.insta.command.post;

import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.like.post.PostUserLikeDto;
import com.insta.api.insta.command.tag.AddTagDto;
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
    private String userName;
    private List<AddTagDto> tagList;
    private List<PostUserLikeDto> postUserLikeList;
    private List<CommentDto> commentList;
}
