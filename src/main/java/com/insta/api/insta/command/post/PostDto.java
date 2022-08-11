package com.insta.api.insta.command.post;

import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.user.UserDto;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
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
    private List<CommentDto> commentList;
}
