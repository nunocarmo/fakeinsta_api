package com.insta.api.insta.command.comment;

import com.insta.api.insta.command.like.UserLikeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CommentDto {
    private Long id;
    private String description;
    private String creationDate;
    private Long userId;
    private List<UserLikeDto> commentUserLikeList;
}
