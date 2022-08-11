package com.insta.api.insta.persistence.repository.like;

import com.insta.api.insta.persistence.model.like.PostUserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IPostUserLikeRepository extends JpaRepository<PostUserLike,Long> {
    @Query(value = "SELECT * FROM post_user_like\n" +
            "WHERE post_user_like.post_id_fk = :postId\n" +
            "AND post_user_like.user_id_fk = :userId ;", nativeQuery = true)
    Optional<PostUserLike> findLikeFromPostAndUser(Long postId,Long userId);
}
