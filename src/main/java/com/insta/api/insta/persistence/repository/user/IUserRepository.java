package com.insta.api.insta.persistence.repository.user;

import com.insta.api.insta.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * \n" +
            "FROM users\n" +
            "WHERE (LOWER(users.username) LIKE (LOWER(CONCAT('%', :username, '%'))) OR :username ISNULL) \n" +
            "AND (LOWER(users.email) LIKE LOWER(CONCAT('%',:email,'%')) OR :email ISNULL) \n" +
            "AND(LOWER(users.name) LIKE LOWER(CONCAT('%',:name,'%')) OR :name ISNULL)", nativeQuery = true)
    List<User> findUsers(String username, String email, String name);

    Optional<User> findByUsername(String username);
}
