package com.insta.api.insta.service.post;

import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.DeletePostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.converter.post.IPostConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.follower.Follower;
import com.insta.api.insta.persistence.model.post.Post;
import com.insta.api.insta.persistence.model.tag.Tag;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.follower.IFollowerRepository;
import com.insta.api.insta.persistence.repository.post.IPostRepository;
import com.insta.api.insta.persistence.repository.tag.ITagRepository;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import com.insta.api.insta.security.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.insta.api.insta.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final ITagRepository tagRepository;
    private final IPostConverter postConverter;
    private final IFollowerRepository followerRepository;
    private final LoggedUser user;
    private final IUserRepository userRepository;

    @Override
    public List<PostDto> getAll() {
        return postConverter.converterList(postRepository.findAll(), PostDto.class);
    }

    @Override
    public PostDto add(AddPostDto addPostDto) {
        addPostDto.setUserId(user.getLoggedUser().getId());
        Post newPost = this.postConverter.converter(addPostDto, Post.class);
        if (newPost.getTagList() != null) {
            for (int i = 0; i < newPost.getTagList().size(); i++) {
                newPost.getTagList().set(i, this.tagRepository.findByTag(newPost.getTagList().get(i).getTag())
                        .orElseThrow(() -> new NotFoundException(TAG_NOT_FOUND)));
            }
        }
        return this.postConverter.converter(this.postRepository.save(newPost), PostDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(DeletePostDto deletePostDto) {
        Post post = this.postRepository.findById(deletePostDto.getPostId())
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));
        if (!post.getUserId().getId().equals(user.getLoggedUser().getId())) {
            throw new NotFoundException(POST_NOT_FROM_USER);
        }
        this.postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<PostDto> searchByTag(String tag) {
        Optional<Tag> tagSearch = this.tagRepository.findByTag(tag);
        if (tagSearch.isEmpty()) {
            return new ArrayList<>();
        }
        List<Post> posts = this.postRepository.searchByTag(tagSearch.get().getId());
        return this.postConverter.converterList(posts, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));
        return this.postConverter.converter(post, PostDto.class);
    }

    @Override
    public List<PostDto> searchPostsByUserName(String name) {
        List<Post> posts = this.postRepository.searchPostsByUserName(name);
        return this.postConverter.converterList(posts, PostDto.class);
    }

    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));
        this.postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<PostDto> getPostsFromFollowing() {
        List<Follower> follows = this.followerRepository.findFollowsByUserId(user.getLoggedUser().getId());
        List<Long> ids = follows.stream().map(follow -> follow.getFollowed().getId()).collect(Collectors.toList());
        User users = this.userRepository.findById(user.getLoggedUser().getId()).get();
        List<Long> allIds = new ArrayList<>(ids);
        allIds.add(users.getId());
        List<Post> allPosts = new ArrayList<>();
        for (Long id : allIds) {
           allPosts.addAll(this.postRepository.findByUserId(id));
        }
        return this.postConverter.converterList(allPosts, PostDto.class);
    }
}
