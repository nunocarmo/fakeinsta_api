package com.insta.api.insta.controller.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.service.comment.ICommentService;
import com.insta.api.insta.service.post.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/comment")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final ICommentService commentService;
    @PostMapping
    public CommentDto addComment(@Valid @RequestBody AddCommentDto addCommentDto) {
        return this.commentService.add(addCommentDto);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteComment(@Valid @RequestBody DeleteCommentDto deleteCommentDto) {
        return this.commentService.delete(deleteCommentDto);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deleteCommentById(@PathVariable("id") Long id) {
        return this.commentService.deleteById(id);
    }
}
