package com.insta.api.insta.service.tag;

import com.insta.api.insta.command.tag.AddTagDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITagService {
    ResponseEntity<Object> add(List<AddTagDto> addTagDto);
}
