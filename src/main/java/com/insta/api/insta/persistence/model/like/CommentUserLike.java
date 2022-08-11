package com.insta.api.insta.persistence.model.like;

import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.model.post.Post;
import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentUserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "comment_id_fk")
    private Comment commentId;
    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User userId;
}
