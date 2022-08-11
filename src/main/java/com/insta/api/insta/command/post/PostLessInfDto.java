package com.insta.api.insta.command.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PostLessInfDto {
    private Long id;
    private String photo;
    private String description;
    private String creationDate;
}
