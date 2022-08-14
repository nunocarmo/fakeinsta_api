package com.insta.api.insta.dataloader;

import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.post.Post;
import com.insta.api.insta.persistence.model.role.Role;
import com.insta.api.insta.persistence.model.tag.Tag;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.comment.ICommentRepository;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.persistence.repository.post.IPostRepository;
import com.insta.api.insta.persistence.repository.role.IRoleRepository;
import com.insta.api.insta.persistence.repository.tag.ITagRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Dataloader implements ApplicationRunner {

    private final IUserRepository userRepository;
    private final IPostRepository postRepository;
    private final ICommentRepository commentRepository;
    private final ITagRepository tagRepository;
    private final IFollowerRepository followerRepository;
    private final PasswordEncoder encoder;
    private final IRoleRepository roleRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Role> roles = getRoles();
        List<Role> rolesFromDB = new ArrayList<>();
        addRolesToDB(roles, rolesFromDB);

        List<User> users = getUsers(rolesFromDB);
        List<User> usersFromDb = new ArrayList<>();
        addUsersToDB(users, usersFromDb);

        List<Tag> tags = getTags();
        List<Tag> tagsFromDb = new ArrayList<>();
        addTagsToDB(tags, tagsFromDb);


        List<Post> postsFromDb = new ArrayList<>();
        List<Post> posts = getPosts(usersFromDb, tagsFromDb);
        addPostsToDB(posts, postsFromDb);


        List<Comment> comments = getComments(usersFromDb, postsFromDb);
        addCommentsToDB(comments);

        Follower follower = Follower
                .builder()
                .followerUser(usersFromDb.get(0))
                .followed(usersFromDb.get(1))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }

    }

    private void addCommentsToDB(List<Comment> comments) {
        comments.forEach(comment -> {
            if (!this.commentRepository.exists(Example.of(comment))) {
                this.commentRepository.save(comment);
            }
        });
    }

    private void addPostsToDB(List<Post> posts, List<Post> postsFromDb) {
        for (int i = 0; i < posts.size(); i++) {
            if (!this.postRepository.exists(Example.of(posts.get(i)))) {
                this.postRepository.save(posts.get(i));
            }
            postsFromDb.add(i, this.postRepository.findById(((i + 1L))).get());
        }
    }

    private void addUsersToDB(List<User> users, List<User> usersFromDb) {
        for (int i = 0; i < users.size(); i++) {
            if (this.userRepository.findByEmail(users.get(i).getEmail()).isEmpty()) {
                this.userRepository.save(users.get(i));
            }
            usersFromDb.add(i, this.userRepository.findById(((i + 1L))).get());
        }
    }

    private void addTagsToDB(List<Tag> tags, List<Tag> tagsFromDb) {
        for (int i = 0; i < tags.size(); i++) {
            if (this.tagRepository.findByTag(tags.get(i).getTag()).isEmpty()) {
                this.tagRepository.save(tags.get(i));
            }
            tagsFromDb.add(this.tagRepository.findById(((i + 1L))).get());
        }

    }

    private void addRolesToDB(List<Role> roles, List<Role> rolesFromDB) {

            for (int i = 0; i < roles.size(); i++) {
                if (!this.roleRepository.exists(Example.of(roles.get(i)))) {
                    this.roleRepository.save(roles.get(i));
                }
                rolesFromDB.add(roles.get(i));
        }
    }

    private List<Comment> getComments(List<User> users, List<Post> posts) {
        return List.of(
                Comment.builder()
                        .description("WOW")
                        .userId(users.get(1))
                        .postId(posts.get(0))
                        .creationDate("1-1-2022")
                        .build(),
                Comment.builder()
                        .description("This is a comment num 2")
                        .userId(users.get(0))
                        .postId(posts.get(0))
                        .creationDate("1-1-2022")
                        .build(),
                Comment.builder()
                        .description("This is a comment num 3")
                        .userId(users.get(1))
                        .postId(posts.get(1))
                        .creationDate("1-1-2022")
                        .build(),
                Comment.builder()
                        .description("This is a comment num 4")
                        .userId(users.get(1))
                        .postId(posts.get(2))
                        .creationDate("1-1-2022")
                        .build()
        );
    }

    private List<Post> getPosts(List<User> users, List<Tag> tags) {
        return List.of(

                Post.builder()
                        .description("dddddddddddddd")
                        .photo("LINK")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(1)))
                        .userId(users.get(1))
                        .build(),

                Post.builder()
                        .description("Isto é um Post")
                        .photo("LINK")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(2), tags.get(0)))
                        .userId(users.get(0))
                        .build(),

                Post.builder()
                        .description("Isto é outro Post")
                        .photo("LINK")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(0)))
                        .userId(users.get(1))
                        .build(),

                Post.builder()
                        .description("Mini golf rocks!")
                        .photo("https://www.flickr.com/photos/196281383@N04/shares/24eKm9GbHw")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(6), tags.get(5), tags.get(4), tags.get(3)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Abbey Road of Ílhavo")
                        .photo("https://www.flickr.com/photos/196281383@N04/shares/4f41f7sP7Z")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(3)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Serious stuff going on here")
                        .photo("https://www.flickr.com/photos/196281383@N04/shares/26Lc9x5jnZ")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(7)))
                        .userId(users.get(3))
                        .build()
        );
    }

    private List<Tag> getTags() {
        return List.of(
                Tag.builder()
                        .tag("#ola")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#adeus")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#top")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#MindSwap")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#afterhours")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#soUmaCerveja")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#minigolf")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#bringSomeKebabs")
                        .postList(null)
                        .build()
        );
    }

    private List<User> getUsers(List<Role> rolesFromDB) {
        return List.of(
                User.builder()
                        .name("Ala Kropa")
                        .username("alakropa")
                        .password(encoder.encode("password"))
                        .email("alakropa@mail.com")
                        .profilePhoto("a photo")
                        .description("Aloha! \uD83D\uDEB2")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Nuno Carmo")
                        .username("nuno")
                        .password(encoder.encode("password"))
                        .email("nuno@mail.com")
                        .profilePhoto("a photo")
                        .description("yesyes")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Carolina Ferraz")
                        .username("carol")
                        .password(encoder.encode("password"))
                        .email("carol@mail.com")
                        .profilePhoto("a photo")
                        .description("Trust me, I'm a Frontend")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Mindswappers from Aveiro")
                        .username("mindeirinhos")
                        .password(encoder.encode("password"))
                        .email("mail@mail.com")
                        .profilePhoto("a photo")
                        .description("Lads, it's friday already")
                        .roleId(rolesFromDB.get(1))
                        .build());
    }

    private List<Role> getRoles() {
        return List.of(
                Role.builder()
                        .name("admin")
                        .build()
                ,
                Role.builder()
                        .name("user")
                        .build());

    }
}
