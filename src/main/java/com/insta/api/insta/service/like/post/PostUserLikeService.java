package com.insta.api.insta.service.like.post;

import com.insta.api.insta.command.like.post.InPostUserLikeDto;
import com.insta.api.insta.command.like.post.PostUserLikeDto;
import com.insta.api.insta.converter.like.ILikeConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.like.PostUserLike;
import com.insta.api.insta.persistence.repository.like.IPostUserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUserLikeService implements IPostUserLikeService{
    private final IPostUserLikeRepository postUserLikeRepository;
    private final ILikeConverter likeConverter;
    @Override
    public PostUserLikeDto add(InPostUserLikeDto addPostUserLikeDto) {
        PostUserLike newPostLike = this.likeConverter.converter(addPostUserLikeDto, PostUserLike.class);
        return this.likeConverter.converter(this.postUserLikeRepository.save(newPostLike), PostUserLikeDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(InPostUserLikeDto removeLikeFromPost) {
        PostUserLike like = this.postUserLikeRepository.findLikeFromPostAndUser(removeLikeFromPost.getPostId(), removeLikeFromPost.getUserId())
                .orElseThrow(() -> new NotFoundException("error"));
        this.postUserLikeRepository.delete(like);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
