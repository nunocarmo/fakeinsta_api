package com.insta.api.insta.persistence.model.post;

import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Column
    private String photo;
    @Column
    private String description;
    @Column
    private String creationDate;
    @OneToMany(mappedBy = "postId", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User userId;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.REMOVE)
    private List<PostUserLike> postUserLikeList;
}
