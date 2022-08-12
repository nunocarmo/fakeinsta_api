package com.insta.api.insta.command.tag;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddTagDto {
    @NotEmpty
    @Pattern(regexp="\\B(\\#[a-zA-Z0-9]+\\b)(?!;)", message = "must start with #")
    private String tag;
}
