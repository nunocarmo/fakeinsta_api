package com.insta.api.insta.service.like.post;

import com.insta.api.insta.command.like.post.InPostUserLikeDto;
import com.insta.api.insta.command.like.post.PostUserLikeDto;
import org.springframework.http.ResponseEntity;

public interface IPostUserLikeService {
    PostUserLikeDto add(InPostUserLikeDto addPostUserLikeDto);

    ResponseEntity<Object> delete(InPostUserLikeDto removeLikeFromPost);
}
