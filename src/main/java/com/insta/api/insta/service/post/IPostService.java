package com.insta.api.insta.service.post;

import com.insta.api.insta.command.post.AddPostDto;
import com.insta.api.insta.command.post.PostDto;

import java.util.List;

public interface IPostService {
    List<PostDto> getAll();

    PostDto add(AddPostDto addPostDto);
}
