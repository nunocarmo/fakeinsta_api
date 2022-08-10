package com.insta.api.insta.converter.like;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter implements ILikeConverter {
    private final ModelMapper modelMapper;

    public LikeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
