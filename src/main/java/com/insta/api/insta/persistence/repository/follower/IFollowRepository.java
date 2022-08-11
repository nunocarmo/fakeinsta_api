package com.insta.api.insta.persistence.repository.follower;

import com.insta.api.insta.persistence.model.follower.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowRepository extends JpaRepository <Follow, Long> {

    @Query(value = "SELECT * \n" +
            "FROM follow\n" +
            "WHERE follow.from_user_fk = :id" , nativeQuery = true)
    List<Follow> findFollowsByUserId(Long id);
}
