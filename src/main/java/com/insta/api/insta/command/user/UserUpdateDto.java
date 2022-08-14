package com.insta.api.insta.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Size(min = 1, max = 35)
    private String name;
    @Size(min = 3, max = 20)
    private String username;
    @Size(min = 8, max = 35)
    private String password;
    @Email
    @Size(max = 40)
    private String email;


    @Size(max = 200)
    private String description;

    @Size(max = 600)
    private String profilePhoto;
}
