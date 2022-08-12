package com.insta.api.insta.command.like.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InPostUserLikeDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id = null;
    @NotNull(message = "post id can't be empty")
    private Long postId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
}
