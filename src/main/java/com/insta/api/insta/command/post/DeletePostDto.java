package com.insta.api.insta.command.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeletePostDto {
    @NotNull(message = "post id can't be empty")
    private Long postId;
}
