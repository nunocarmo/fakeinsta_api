package com.insta.api.insta.service.post;

import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.DeletePostDto;
import com.insta.api.insta.command.post.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPostService {
    List<PostDto> getAll();

    PostDto add(AddPostDto addPostDto);

    ResponseEntity<Object> delete(DeletePostDto deletePostDto);

    List<PostDto> searchByTag(String tag);

    PostDto getPostById(Long id);

    List<PostDto> searchPostsByUserName(String name);
}
