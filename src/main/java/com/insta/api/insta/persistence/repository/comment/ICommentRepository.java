package com.insta.api.insta.persistence.repository.comment;

import com.insta.api.insta.persistence.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
