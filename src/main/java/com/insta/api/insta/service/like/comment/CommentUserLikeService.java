package com.insta.api.insta.service.like.comment;

import com.insta.api.insta.command.like.comment.InCommentUserLikeDto;
import com.insta.api.insta.command.like.comment.CommentUserLikeDto;
import com.insta.api.insta.converter.like.ILikeConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.like.CommentUserLike;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.repository.like.ICommentUserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentUserLikeService implements ICommentUserLikeService {
    private final ICommentUserLikeRepository commentUserLikeRepository;
    private final ILikeConverter likeConverter;

    @Override
    public CommentUserLikeDto add(InCommentUserLikeDto addCommentUserLikeDto) {
        CommentUserLike newCommentLike = this.likeConverter.converter(addCommentUserLikeDto, CommentUserLike.class);
        return this.likeConverter.converter(this.commentUserLikeRepository.save(newCommentLike), CommentUserLikeDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(InCommentUserLikeDto removeLikeFromComment) {
        CommentUserLike like = this.commentUserLikeRepository
                .findLikeFromCommentAndUser(removeLikeFromComment.getCommentId(),removeLikeFromComment.getUserId())
                .orElseThrow(() -> new NotFoundException("error"));
        this.commentUserLikeRepository.delete(like);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
