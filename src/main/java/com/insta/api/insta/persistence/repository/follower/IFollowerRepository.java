package com.insta.api.insta.persistence.repository.follower;

import com.insta.api.insta.persistence.model.follower.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IFollowerRepository extends JpaRepository <Follower, Long> {

    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.followed_user_fk = users.id\n" +
            "WHERE users.id = :id", nativeQuery = true)
    List<Follower> findFollowersByUserId(Long id);



    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.follower_user_fk = users.id\n" +
            "WHERE users.id = :id", nativeQuery = true)
    List<Follower> findFollowsByUserId(Long id);


    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.follower_user_fk = users.id\n" +
            "WHERE users.id = :id AND followed_user_fk = :idToFollow", nativeQuery = true)
    List<Follower> checkIfUserFollowsAlready(Long id, Long idToFollow);

    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.follower_user_fk = users.id\n" +
            "WHERE users.id = :followerId AND followed_user_fk = :followedId", nativeQuery = true)
    Optional<Follower> findFollowerAndFollowedMatch(Long followerId, Long followedId);
}

