package com.insta.api.insta.persistence.model.user;

import com.insta.api.insta.persistence.model.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = false, updatable = true)
    private String name;

    @Column(nullable = false, unique = true, updatable = true)
    private String username;

    @Column(nullable = false, unique = false, updatable = true)
    private String password;

    @Column(nullable = false, unique = true, updatable = true)
    private String email;

    @Column(nullable = true, unique = false, updatable = true)
    private String description;

    @Column(nullable = true, unique = false, updatable = true, length = 64)
    private String profilePhoto;

    //@OneToMany(mappedBy="postId")
    private List<Post> addedPosts;

    @OneToMany(mappedBy="to")
    private List<Follower> followers;

    @OneToMany(mappedBy="from")
    private List<Follower> following;

}
