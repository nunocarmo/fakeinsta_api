package com.insta.api.insta.command.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddPostDto {
    private String photo;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
}
