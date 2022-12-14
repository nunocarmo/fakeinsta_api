package com.insta.api.insta.persistence.model.follower;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "follower_user_fk")
    private User followerUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "followed_user_fk")
    private User followed;


}
