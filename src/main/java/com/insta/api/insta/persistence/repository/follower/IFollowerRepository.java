package com.insta.api.insta.persistence.repository.follower;

import com.insta.api.insta.persistence.model.follower.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowerRepository extends JpaRepository <Follower, Long> {

    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.from_user_fk = users.id\n" +
            "WHERE users.id = :id", nativeQuery = true)

    /*@Query(value = "SELECT * \n" +
            "FROM followers\n" +
            "WHERE followers.from_user_fk = :id" , nativeQuery = true)*/
    List<Follower> findFollowersByUserId(Long id);



    @Query(value = "SELECT *\n" +
            "FROM followers \n" +
            "INNER JOIN\n" +
            "users \n" +
            "ON followers.to_user_fk = users.id\n" +
            "WHERE users.id = :id", nativeQuery = true)
    List<Follower> findFollowsByUserId(Long id);
}
