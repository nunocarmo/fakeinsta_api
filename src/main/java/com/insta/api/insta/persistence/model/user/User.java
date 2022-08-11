package com.insta.api.insta.persistence.model.user;

import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "userId")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "userId")
    private List<Post> postList;

   //@OneToMany(mappedBy = "id")
   // private List<Follower> followers = new ArrayList<>();

    @OneToMany(mappedBy = "id")
    private List<Follower> following = new ArrayList<>();

    //relations

}
