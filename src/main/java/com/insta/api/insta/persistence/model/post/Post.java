package com.insta.api.insta.persistence.model.post;

import com.insta.api.insta.persistence.model.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
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

    @OneToMany(mappedBy = "postsId")
    private List<Comment> commentList;
}
