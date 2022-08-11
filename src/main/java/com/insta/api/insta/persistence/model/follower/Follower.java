package com.insta.api.insta.persistence.model.follower;

import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_fk")
    private User followerUser;


    @ManyToOne
    @JoinColumn(name = "followed_user_fk")
    private User followed;


}
