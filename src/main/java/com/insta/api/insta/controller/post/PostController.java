package com.insta.api.insta.controller.post;

import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.DeletePostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.service.post.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    private IPostService postService;


    @Cacheable(value = "posts")
    @GetMapping
    public List<PostDto> getPosts() {
        System.out.println("Getting posts from DB");
        return this.postService.getAll();
    }

    @CacheEvict(value = {"posts"}, allEntries = true)
    @PostMapping
    public PostDto addPost(@Valid @RequestBody AddPostDto addPostDto) {
        return this.postService.add(addPostDto);
    }

    @CacheEvict(value = {"posts"}, allEntries = true)
    @DeleteMapping
    public ResponseEntity<Object> deletePost(@Valid @RequestBody DeletePostDto deletePostDto) {
        return this.postService.delete(deletePostDto);
    }

    @CacheEvict(value = {"posts"}, allEntries = true)
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("id") Long id) {
        return this.postService.deleteById(id);
    }

    @Cacheable(value = "posts")
    @GetMapping("/search/tag")
    public List<PostDto> getPostsByTag(@RequestParam(value = "tag") String tag) {
        return this.postService.searchByTag(tag);
    }

    @Cacheable(value = "posts")
    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable("id") Long id) {
        System.out.println("Getting post from DB");
        return this.postService.getPostById(id);
    }

    @GetMapping("/search")
    public List<PostDto> getPostById(@RequestParam(value = "name") String name) {
        return this.postService.searchPostsByUserName(name);
    }


    @GetMapping("/user")
    public List<PostDto> getPostByUserId() {
        return this.postService.getPostsByUserId();
    }

    @GetMapping("/following")
    public List<PostDto> getFromFollowing() {
        return this.postService.getPostsFromFollowing();
    }
}
