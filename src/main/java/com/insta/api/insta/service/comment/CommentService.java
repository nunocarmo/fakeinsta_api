package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.command.comment.DeleteCommentDto;
import com.insta.api.insta.converter.comment.ICommentConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.repository.comment.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLTableRowElement;

import static com.insta.api.insta.exception.ExceptionMessages.COMMENT_NOT_FROM_USER;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final ICommentRepository commentRepository;
    private final ICommentConverter commentConverter;
    @Override
    public CommentDto add(AddCommentDto addCommentDto) {
        Comment newComment = this.commentConverter.converter(addCommentDto, Comment.class);
        return this.commentConverter.converter(this.commentRepository.saveAndFlush(newComment), CommentDto.class);
    }

    @Override
    public CommentDto delete(DeleteCommentDto deleteCommentDto) {
        Comment comment = this.commentRepository.findById(deleteCommentDto.getCommentId()).get();
        if(!comment.getUserId().getId().equals(deleteCommentDto.getUserId())){
            throw new NotFoundException(COMMENT_NOT_FROM_USER);
        }
        CommentDto commentDto = this.commentConverter.converter(comment,CommentDto.class);
        this.commentRepository.delete(comment);
        return commentDto;
    }
}
