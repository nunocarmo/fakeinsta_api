package com.insta.api.insta.command.like.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InCommentUserLikeDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id = null;
    @NotNull(message = "comment id can't be empty")
    private Long commentId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
}
