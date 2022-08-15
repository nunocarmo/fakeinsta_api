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

        //List<Follower> followersFromDB = new ArrayList<>();
        // List<Follower> followers = getFollowers(usersFromDb);
        //  addFollowersToDB(followers, followersFromDB);

        Follower follower = Follower
                .builder()
                .followerUser(usersFromDb.get(0))
                .followed(usersFromDb.get(1))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }


        Follower follower2 = Follower
                .builder()
                .followerUser(usersFromDb.get(0))
                .followed(usersFromDb.get(2))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }


        Follower follower3 = Follower
                .builder()
                .followerUser(usersFromDb.get(0))
                .followed(usersFromDb.get(3))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }

        Follower follower4 = Follower
                .builder()
                .followerUser(usersFromDb.get(1))
                .followed(usersFromDb.get(3))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }

        Follower follower5 = Follower
                .builder()
                .followerUser(usersFromDb.get(2))
                .followed(usersFromDb.get(3))
                .build();
        if (this.followerRepository
                .findFollowerAndFollowedMatch(follower.getFollowerUser().getId(), follower.getFollowed().getId()).isEmpty()) {
            this.followerRepository.save(follower);
        }

        Follower follower6 = Follower
                .builder()
                .followerUser(usersFromDb.get(1))
                .followed(usersFromDb.get(2))
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
                        .description("I'm lovin' it")
                        .userId(users.get(0))
                        .postId(posts.get(0))
                        .creationDate("1-1-2022")
                        .build(),
                Comment.builder()
                        .description("Nice pic")
                        .userId(users.get(1))
                        .postId(posts.get(5))
                        .creationDate("1-1-2022")
                        .build(),
                Comment.builder()
                        .description("\uD83D\uDC9B")
                        .userId(users.get(1))
                        .postId(posts.get(8))
                        .creationDate("1-1-2022")
                        .build()
        );
    }

    private List<Post> getPosts(List<User> users, List<Tag> tags) {
        return List.of(

                Post.builder()
                        .description("Sunset")
                        .photo("https://i.imgur.com/sFIReIZ.jpg")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(1)))
                        .userId(users.get(1))
                        .build(),

                Post.builder()
                        .description("Mindswap")
                        .photo("https://i.imgur.com/yfS5zkm.jpg")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(2), tags.get(0)))
                        .userId(users.get(0))
                        .build(),

                Post.builder()
                        .description("Raw code")
                        .photo("https://i.imgur.com/FAfQut6.jpg")
                        .creationDate("1-1-2022")
                        .tagList(List.of(tags.get(0)))
                        .userId(users.get(1))
                        .build(),

                Post.builder()
                        .description("Ping pong rocks!")
                        .photo("https://i.imgur.com/UvU0M8O.jpg")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(3), tags.get(4), tags.get(5)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Abbey Road of Ílhavo")
                        .photo("https://i.imgur.com/ykzHaUG.jpg")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(3)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Serious stuff going on here")
                        .photo("https://i.imgur.com/2yX2tF3.jpg")
                        .creationDate("14-08-2022")
                        .tagList(List.of(tags.get(7)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Another sunset")
                        .photo("https://i.imgur.com/2yX2tF3.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(8), tags.get(9)))
                        .userId(users.get(1))
                        .build(),
                Post.builder()
                        .description("Sky")
                        .photo("https://i.imgur.com/HGMFuHW.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(9)))
                        .userId(users.get(1))
                        .build(),
                Post.builder()
                        .description("PCI, Ílhavo")
                        .photo("https://i.imgur.com/MO4hBhr.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(9),tags.get(1), tags.get(2)))
                        .userId(users.get(1))
                        .build(),
                Post.builder()
                        .description("Night sky \uD83C\uDF1D")
                        .photo("https://i.imgur.com/0GkzUF1.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(9)))
                        .userId(users.get(2))
                        .build(),
                Post.builder()
                        .description("Sky \uD83C\uDF1D")
                        .photo("https://i.imgur.com/BTaAKxa.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(2)))
                        .userId(users.get(2))
                        .build(),
                Post.builder()
                        .description("Uma memória da floresta")
                        .photo("https://i.imgur.com/dJVrSha.jpg")
                        .creationDate("15-08-2022")
                        .userId(users.get(0))
                        .build(),
                Post.builder()
                        .description("New haircut")
                        .photo("https://i.imgur.com/08sGLbN.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(0)))
                        .userId(users.get(0))
                        .build(),
                Post.builder()
                        .description("Best crab spotting spot :) \uD83E\uDD80")
                        .photo("https://i.imgur.com/TmVUW3u.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Amigo")
                        .photo("https://i.imgur.com/3EulFTU.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Salinas")
                        .photo("https://i.imgur.com/CjwquPr.jpg")
                        .creationDate("15-08-2022")
                        .userId(users.get(0))
                        .build(),
                Post.builder()
                        .description("I want to ride by bicycle, I want to ride my bike!")
                        .photo("https://i.imgur.com/vSm8IIi.jpg")
                        .creationDate("15-08-2022")
                        .userId(users.get(0))
                        .build(),
                Post.builder()
                        .description("Coffee break :)")
                        .photo("https://i.imgur.com/7PSfr2U.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3), tags.get(10)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Ping pong madness: Ala")
                        .photo("https://i.imgur.com/iLFsXLf.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3), tags.get(11)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Ping pong madness: Luís")
                        .photo("https://i.imgur.com/p7Xo13P.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3), tags.get(11)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Ping pong madness: João")
                        .photo("https://i.imgur.com/BaUSimm.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3), tags.get(11)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Ping pong madness: Elisa")
                        .photo("https://i.imgur.com/8zu1GkM.jpg")
                        .creationDate("15-08-2022")
                        .tagList(List.of(tags.get(1), tags.get(3), tags.get(11)))
                        .userId(users.get(3))
                        .build(),
                Post.builder()
                        .description("Creepy but nice lavender")
                        .photo("https://i.imgur.com/as4YBZG.jpg")
                        .creationDate("15-08-2022")
                        .userId(users.get(2))
                        .build(),
                Post.builder()
                        .description("Forest")
                        .photo("https://i.imgur.com/DrD7yaU.jpg")
                        .creationDate("15-08-2022")
                        .userId(users.get(2))
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
                        .build(),
                Tag.builder()
                        .tag("#sunset")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#sky")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#coffeeBreak")
                        .postList(null)
                        .build(),
                Tag.builder()
                        .tag("#PingPong")
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
                        .profilePhoto("https://i.imgur.com/TOfNlVB.jpg")
                        .description("Aloha! \uD83D\uDEB2")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Nuno Carmo")
                        .username("nuno")
                        .password(encoder.encode("password"))
                        .email("nuno@mail.com")
                        .profilePhoto("https://freesvg.org/img/anon-hacker-behind-pc.png")
                        .description("yesyes")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Carolina Ferraz")
                        .username("carol")
                        .password(encoder.encode("password"))
                        .email("carol@mail.com")
                        .profilePhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRr_sOogl_7U1m1-Rs1QqlO-R-8jXF1YklSyg&usqp=CAU")
                        .description("Trust me, I'm a Frontend")
                        .roleId(rolesFromDB.get(0))
                        .build()
                ,
                User.builder()
                        .name("Mindswappers from Aveiro")
                        .username("mindeirinhos")
                        .password(encoder.encode("password"))
                        .email("mail@mail.com")
                        .profilePhoto("https://i.imgur.com/yfS5zkm.jpg")
                        .description("It's friday already!")
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
