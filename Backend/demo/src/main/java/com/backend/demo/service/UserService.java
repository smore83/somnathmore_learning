package com.backend.demo.service;

import com.backend.demo.dto.PostDto;
import com.backend.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();

    public UserDto createUser(UserDto userDto);

    public String saveUser(UserDto userDto);

    public String updateUser(UserDto userDto, Long userId);

    public String savePostByUserId(PostDto postDto, Long userId);
}
