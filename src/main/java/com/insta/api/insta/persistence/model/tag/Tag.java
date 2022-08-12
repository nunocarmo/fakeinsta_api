package com.insta.api.insta.persistence.model.tag;

import com.insta.api.insta.persistence.model.post.Post;
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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    private String tag;
    @ManyToMany(mappedBy = "tagList")
    private List<Post> postList;
}
