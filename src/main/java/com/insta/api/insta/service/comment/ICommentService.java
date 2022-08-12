package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import org.springframework.http.ResponseEntity;

public interface ICommentService {
    CommentDto add(AddCommentDto addCommentDto);

    ResponseEntity<Object> delete(DeleteCommentDto deleteCommentDto);
}
