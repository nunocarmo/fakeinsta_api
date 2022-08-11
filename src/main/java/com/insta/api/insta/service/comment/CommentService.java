package com.insta.api.insta.service.comment;

import com.insta.api.insta.command.comment.AddCommentDto;
import com.insta.api.insta.command.comment.CommentDto;
import com.insta.api.insta.converter.comment.ICommentConverter;
import com.insta.api.insta.persistence.model.comment.Comment;
import com.insta.api.insta.persistence.repository.comment.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final ICommentRepository commentRepository;
    private final ICommentConverter commentConverter;
    @Override
    public CommentDto add(AddCommentDto addCommentDto) {
        Comment newComment = this.commentConverter.converter(addCommentDto, Comment.class);
        System.out.println(newComment);
        return this.commentConverter.converter(this.commentRepository.saveAndFlush(newComment), CommentDto.class);
    }
}
