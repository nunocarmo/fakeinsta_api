package com.insta.api.insta.converter.post;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements IPostConverter {
    private final ModelMapper modelMapper;

    public PostConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
