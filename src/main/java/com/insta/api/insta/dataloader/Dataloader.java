package com.insta.api.insta.dataloader;

import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.comment.ICommentRepository;
import com.insta.api.insta.persistence.repository.post.IPostRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Dataloader implements ApplicationRunner {

    private final IUserRepository userRepository;
    private final IPostRepository postRepository;
    private final ICommentRepository commentRepository;
    private final PasswordEncoder encoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = List.of(
                User.builder()
                        .name("Ala Kropa")
                        .username("alakropa")
                        .password(encoder.encode("password"))
                        .email("alakropa@mail.com")
                        .profilePhoto("a photo")
                        .description("alakropa@mail.com")
                        .build()
                ,
                User.builder()
                        .name("Nuno Carmo")
                        .username("nuno")
                        .password(encoder.encode("password"))
                        .email("nuno@mail.com")
                        .profilePhoto("a photo")
                        .description("yesyes")
                        .build());

        users.forEach(user -> {
            if (this.userRepository.findByEmail(user.getEmail()).isEmpty()) {
                this.userRepository.save(user);
            }
        });
    }
}
