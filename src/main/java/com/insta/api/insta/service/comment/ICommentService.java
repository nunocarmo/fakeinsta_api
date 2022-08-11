package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;

public interface ICommentService {
    CommentDto add(AddCommentDto addCommentDto);

    CommentDto delete(DeleteCommentDto deleteCommentDto);
}
