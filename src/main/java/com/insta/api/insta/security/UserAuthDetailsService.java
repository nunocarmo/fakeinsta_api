package com.insta.api.insta.security;

import com.insta.api.insta.exception.BadRequestException;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.insta.api.insta.exception.ExceptionMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));

        return UserAuth.builder()
                .user(user)
                .build();

    }
}

