package com.insta.api.insta.service.post;

import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.DeletePostDto;
import com.insta.api.insta.command.post.PostDto;
import com.insta.api.insta.converter.post.IPostConverter;
import com.insta.api.insta.exception.NotFoundException;
import com.insta.api.insta.persistence.model.post.Post;
import com.insta.api.insta.persistence.repository.post.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final IPostConverter postConverter;

    @Override
    public List<PostDto> getAll() {
        return postConverter.converterList(postRepository.findAll(), PostDto.class);
    }

    @Override
    public PostDto add(AddPostDto addPostDto) {
        Post newPost = this.postConverter.converter(addPostDto, Post.class);
        return this.postConverter.converter(this.postRepository.save(newPost), PostDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(DeletePostDto deletePostDto) {
       Post post = this.postRepository.findById(deletePostDto.getPostId()).get();
       if(!post.getUserId().getId().equals(deletePostDto.getUserId())){
           throw new NotFoundException("Post From this User Doesn't Exist");
       }
        this.postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
