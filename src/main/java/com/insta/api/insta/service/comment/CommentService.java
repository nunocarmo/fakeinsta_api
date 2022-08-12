package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import com.insta.api.insta.converter.comment.ICommentConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.repository.comment.ICommentRepository;
import com.insta.api.insta.security.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLTableRowElement;

import static com.insta.api.insta.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final ICommentRepository commentRepository;
    private final ICommentConverter commentConverter;
    private final LoggedUser user;
    @Override
    public CommentDto add(AddCommentDto addCommentDto) {
        addCommentDto.setUserId(user.getLoggedUser().getId());
        Comment newComment = this.commentConverter.converter(addCommentDto, Comment.class);
        return this.commentConverter.converter(this.commentRepository.saveAndFlush(newComment), CommentDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(DeleteCommentDto deleteCommentDto) {

        Comment comment = this.commentRepository.findById(deleteCommentDto.getCommentId())
                .orElseThrow(()-> new NotFoundException(COMMENT_NOT_FOUND));;
        if(!comment.getUserId().getId().equals(user.getLoggedUser().getId())){
            throw new NotFoundException(COMMENT_NOT_FROM_USER);
        }
        CommentDto commentDto = this.commentConverter.converter(comment,CommentDto.class);
        this.commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
