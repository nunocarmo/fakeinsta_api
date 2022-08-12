package com.insta.api.insta.service.tag;

import com.insta.api.insta.command.tag.AddTagDto;
import com.insta.api.insta.converter.tag.ITagConverter;
import com.insta.api.insta.persistence.model.tag.Tag;
import com.insta.api.insta.persistence.repository.tag.ITagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final ITagRepository tagRepository;
    private final ITagConverter tagConverter;
    @Override
    public ResponseEntity<Object> add(List<AddTagDto> addTagDto) {
        List<Tag> tags = addTagDto.stream().map(tag -> tagConverter.converter(tag, Tag.class)).toList();
        tags.forEach(tag -> {
            if (this.tagRepository.findByTag(tag.getTag()).isEmpty()) this.tagRepository.save(tag);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
