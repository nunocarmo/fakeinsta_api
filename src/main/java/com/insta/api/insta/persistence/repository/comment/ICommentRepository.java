package com.insta.api.insta.persistence.repository.comment;

import com.insta.api.insta.persistence.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
