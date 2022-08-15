package com.insta.api.insta.controller.tag;

import com.insta.api.insta.command.tag.AddTagDto;
import com.insta.api.insta.service.tag.ITagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/tag")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {
    private final ITagService tagService;
    @PostMapping
    public ResponseEntity<Object> addTag(@Valid @RequestBody List<AddTagDto> addTagDto) {
        return this.tagService.add(addTagDto);
    }
}
