package com.insta.api.insta.persistence.model.like;

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
public class PostUserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id_fk")
    private Post postId;
    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User userId;
}
