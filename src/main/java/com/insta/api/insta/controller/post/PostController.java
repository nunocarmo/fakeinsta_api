package com.insta.api.insta.controller.post;

import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.DeletePostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.service.post.IPostService;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public List<PostDto> getPosts() {
        return this.postService.getAll();
    }

    @PostMapping
    public PostDto addPost(@Valid @RequestBody AddPostDto addPostDto) {
        return this.postService.add(addPostDto);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePost(@Valid @RequestBody DeletePostDto deletePostDto) {
        return this.postService.delete(deletePostDto);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("id") Long id) {
        return this.postService.deleteById(id);
    }

    @GetMapping("/search/tag")
    public List<PostDto> getPostsByTag(@RequestParam(value = "tag") String tag) {
        return this.postService.searchByTag(tag);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable("id") Long id) {
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
