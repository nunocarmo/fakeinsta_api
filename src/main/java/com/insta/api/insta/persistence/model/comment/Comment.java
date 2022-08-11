package com.insta.api.insta.persistence.model.comment;

import com.insta.api.insta.persistence.model.like.CommentUserLike;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.model.post.Post;
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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Column
    private String description;
    @Column
    private String creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "post_id_fk")
    private Post postId;

    @OneToMany(mappedBy = "commentId", cascade = CascadeType.REMOVE)
    private List<CommentUserLike> commentUserLikeList;
}
