package com.insta.api.insta.persistence.repository.post;

import com.insta.api.insta.persistence.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    @Query(value = "SELECT post.* FROM tag\n" +
            "INNER JOIN\n" +
            "tag_post_list\n" +
            "ON tag.id = tag_post_list.tag_id\n" +
            "INNER JOIN post\n" +
            "ON post.id = tag_post_list.post_id\n" +
            "WHERE tag.id = :tag", nativeQuery = true)
    List<Post> searchByTag(Long tag);
    @Query(value = "SELECT post.* FROM post\n" +
            "INNER JOIN\n" +
            "users\n" +
            "ON users.id = post.user_id_fk\n" +
            "WHERE LOWER(users.name)  LIKE LOWER(CONCAT('%',:name,'%'))", nativeQuery = true)
    List<Post> searchPostsByUserName(String name);

    List<Post> findByIdIn(List<Long> ids);
}
