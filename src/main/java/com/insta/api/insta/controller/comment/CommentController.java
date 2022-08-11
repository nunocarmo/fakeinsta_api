package com.insta.api.insta.controller.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.service.comment.ICommentService;
import com.insta.api.insta.service.post.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comment")
@AllArgsConstructor
public class CommentController {

    private final ICommentService commentService;
    @PostMapping
    public CommentDto addComment(/*@Valid*/ @RequestBody AddCommentDto addCommentDto) {
        return this.commentService.add(addCommentDto);
    }
}
