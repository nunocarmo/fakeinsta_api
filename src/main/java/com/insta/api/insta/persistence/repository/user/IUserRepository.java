package com.insta.api.insta.persistence.repository.user;

import com.insta.api.insta.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

 @Query(value = "SELECT * \n" +
            "FROM users\n" +
            "WHERE UPPER(users.username) LIKE (UPPER(CONCAT('%', :username, '%')))", nativeQuery = true)
    List<User> findUsersByUsername(String username);

    Optional<User> findByUsername(String username);
}
