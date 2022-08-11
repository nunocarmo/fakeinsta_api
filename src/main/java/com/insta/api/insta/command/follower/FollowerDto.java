package com.insta.api.insta.command.follower;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.api.insta.persistence.model.user.User;

import javax.persistence.*;

public class FollowerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

  //  private User from;

  //  private User to;
}
