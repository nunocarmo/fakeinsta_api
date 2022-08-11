package com.insta.api.insta.persistence.repository.post;

import com.insta.api.insta.persistence.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
}
