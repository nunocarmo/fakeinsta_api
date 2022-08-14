package com.insta.api.insta.command.user;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLessInfoDto {
    private Long id;
    private String name;
}
