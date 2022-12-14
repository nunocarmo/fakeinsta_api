package com.insta.api.insta.controller.like;

import com.insta.api.insta.command.like.comment.InCommentUserLikeDto;
import com.insta.api.insta.command.like.comment.CommentUserLikeDto;
import com.insta.api.insta.command.like.post.InPostUserLikeDto;
import com.insta.api.insta.command.like.post.PostUserLikeDto;
import com.insta.api.insta.service.like.comment.ICommentUserLikeService;
import com.insta.api.insta.service.like.post.IPostUserLikeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/like")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LikeController {
    private final IPostUserLikeService postUserLikeService;
    private final ICommentUserLikeService commentUserLikeService;
    @CacheEvict(value = {"posts", "users"}, allEntries = true)
    @PostMapping("/post")
    public PostUserLikeDto addLikeToPost(@Valid @RequestBody InPostUserLikeDto addPostUserLikeDto) {
        return this.postUserLikeService.add(addPostUserLikeDto);
    }
    @CacheEvict(value = {"posts", "users"}, allEntries = true)
    @PostMapping("/comment")
    public CommentUserLikeDto addLikeToComment(@Valid @RequestBody InCommentUserLikeDto addCommentUserLikeDto) {
        return this.commentUserLikeService.add(addCommentUserLikeDto);
    }
    @CacheEvict(value = {"posts", "users"}, allEntries = true)
    @DeleteMapping("/post")
    public ResponseEntity<Object> deleteLikeToPost(@Valid @RequestBody InPostUserLikeDto removeLikeFromPost) {
        return this.postUserLikeService.delete(removeLikeFromPost);
    }
    @CacheEvict(value = {"posts", "users"}, allEntries = true)
    @DeleteMapping("/comment")
    public ResponseEntity<Object> deleteLikeToPost(@Valid @RequestBody InCommentUserLikeDto removeLikeFromComment) {
        return this.commentUserLikeService.delete(removeLikeFromComment);
    }
}
