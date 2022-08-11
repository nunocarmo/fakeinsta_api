package com.insta.api.insta.persistence.repository.user;

import com.insta.api.insta.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <User, Long> {
}
