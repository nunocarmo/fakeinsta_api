package com.insta.api.insta.persistence.repository.user;

import com.insta.api.insta.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

/*    @Query(value = "SELECT * \n" +
            "FROM users\n" +
            "WHERE users.username LIKE ('%', :username, '%')", nativeQuery = true)
    List<User> findByUsername(String username);*/

    User findByUsername(String username);
}
