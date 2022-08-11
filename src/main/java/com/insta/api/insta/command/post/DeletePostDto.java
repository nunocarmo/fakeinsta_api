package com.insta.api.insta.command.post;

import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeletePostDto {
    @NotNull(message = "user id can't be empty")
    private Long userId;
    @NotNull(message = "post id can't be empty")
    private Long postId;
}
