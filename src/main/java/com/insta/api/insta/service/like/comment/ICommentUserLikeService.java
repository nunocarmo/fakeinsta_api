package com.insta.api.insta.service.like.comment;

import com.insta.api.insta.command.like.comment.InCommentUserLikeDto;
import com.insta.api.insta.command.like.comment.CommentUserLikeDto;
import org.springframework.http.ResponseEntity;

public interface ICommentUserLikeService {
    CommentUserLikeDto add(InCommentUserLikeDto addCommentUserLikeDto);

    ResponseEntity<Object> delete(InCommentUserLikeDto removeLikeFromComment);
}
