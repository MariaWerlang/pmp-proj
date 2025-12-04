package com.duda.web.dto.mapper;

import com.duda.entity.User;
import com.duda.web.dto.CreateDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(CreateDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

}
