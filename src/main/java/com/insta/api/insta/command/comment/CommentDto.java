package com.insta.api.insta.command.comment;

import lombok.*;

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
}
