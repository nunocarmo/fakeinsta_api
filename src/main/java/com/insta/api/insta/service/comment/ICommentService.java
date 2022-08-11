package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;

public interface ICommentService {
    CommentDto add(AddCommentDto addCommentDto);
}
