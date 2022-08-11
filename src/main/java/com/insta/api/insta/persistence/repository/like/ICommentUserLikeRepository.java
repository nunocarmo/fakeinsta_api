package com.insta.api.insta.persistence.repository.like;

import com.insta.api.insta.persistence.model.like.CommentUserLike;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentUserLikeRepository extends JpaRepository<CommentUserLike,Long> {
    @Query(value = "SELECT * FROM comment_user_like\n" +
            "WHERE comment_user_like.comment_id_fk = :commentId\n" +
            "AND comment_user_like.user_id_fk = :userId ;", nativeQuery = true)
    Optional<CommentUserLike> findLikeFromCommentAndUser(Long commentId, Long userId);
}
