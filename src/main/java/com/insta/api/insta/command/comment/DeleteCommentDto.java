package com.insta.api.insta.command.comment;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeleteCommentDto {
    @NotNull(message = "comment id can't be empty")
    private Long commentId;
}
