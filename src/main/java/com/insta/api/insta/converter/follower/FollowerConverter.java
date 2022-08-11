package com.insta.api.insta.converter.follower;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowerConverter implements IFollowerConverter {

    private final ModelMapper modelMapper;

    public FollowerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
