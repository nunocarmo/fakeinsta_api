package com.insta.api.insta.command.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddPostDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id = null;
    private String photo;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long likeCount = 0L;
    @NotNull(message = "user id can't be empty")
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
}
