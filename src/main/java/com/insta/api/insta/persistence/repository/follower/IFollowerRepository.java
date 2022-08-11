package com.insta.api.insta.persistence.repository.follower;

import com.insta.api.insta.persistence.model.follower.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowerRepository extends JpaRepository <Follower, Long> {
    @Query(value = "SELECT * \n" +
            "FROM followers\n" +
            "WHERE followers.to.id = :id" , nativeQuery = true)
    List<Follower> findFollowersByUserId(Long id);
}
