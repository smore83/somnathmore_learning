package com.backend.demo.utils;

import com.backend.demo.dto.UserDto;
import com.backend.demo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converters {
     @Autowired
    private ModelMapper modelMapper;

     public UserDto entityToDto(User user){
         return modelMapper.map(user, UserDto.class);

     }

    public User dtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);

    }
}
