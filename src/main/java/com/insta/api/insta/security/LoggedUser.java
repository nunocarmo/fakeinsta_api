package com.insta.api.insta.security;

import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.insta.api.insta.exception.ExceptionMessages.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class LoggedUser {
    private final IUserRepository userRepository;
    public User getLoggedUser(){
      String userEmail =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
      return this.userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
    }
}
