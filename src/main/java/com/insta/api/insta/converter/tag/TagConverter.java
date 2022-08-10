package com.insta.api.insta.converter.tag;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TagConverter implements ITagConverter {
    private final ModelMapper modelMapper;

    public TagConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
