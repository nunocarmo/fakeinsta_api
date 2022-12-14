package com.insta.api.insta.service.like.comment;

import com.insta.api.insta.command.like.comment.InCommentUserLikeDto;
import com.insta.api.insta.command.like.comment.CommentUserLikeDto;
import com.insta.api.insta.converter.like.ILikeConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.like.CommentUserLike;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.repository.like.ICommentUserLikeRepository;
import com.insta.api.insta.security.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.insta.api.insta.exception.ExceptionMessages.LIKE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentUserLikeService implements ICommentUserLikeService {
    private final ICommentUserLikeRepository commentUserLikeRepository;
    private final ILikeConverter likeConverter;
    private final LoggedUser user;
    @Override
    public CommentUserLikeDto add(InCommentUserLikeDto addCommentUserLikeDto) {
        addCommentUserLikeDto.setUserId(user.getLoggedUser().getId());
        CommentUserLike newCommentLike = this.likeConverter.converter(addCommentUserLikeDto, CommentUserLike.class);
        return this.likeConverter.converter(this.commentUserLikeRepository.save(newCommentLike), CommentUserLikeDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(InCommentUserLikeDto removeLikeFromComment) {
        removeLikeFromComment.setUserId(user.getLoggedUser().getId());
        CommentUserLike like = this.commentUserLikeRepository
                .findLikeFromCommentAndUser(removeLikeFromComment.getCommentId(),removeLikeFromComment.getUserId())
                .orElseThrow(() -> new NotFoundException(LIKE_NOT_FOUND));
        this.commentUserLikeRepository.delete(like);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
