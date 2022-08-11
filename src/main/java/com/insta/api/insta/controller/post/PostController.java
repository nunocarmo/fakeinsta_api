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

}
