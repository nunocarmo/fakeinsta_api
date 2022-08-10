package com.insta.api.insta.converter.comment;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements ICommentConverter {
    private final ModelMapper modelMapper;

    public CommentConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
