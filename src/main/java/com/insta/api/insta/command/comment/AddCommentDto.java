package com.insta.api.insta.command.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.persistence.model.post.Post;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddCommentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id = null;
    @NotNull(message = "Can't post an empty comment")
    private String description;
    @NotNull(message = "post id can't be empty")
    private Long userId;
    @NotNull(message = "post id can't be empty")
    private Long postId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
}
