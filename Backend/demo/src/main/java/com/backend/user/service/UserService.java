package com.backend.user.service;

import com.backend.user.dto.PostDto;
import com.backend.user.dto.UserDto;
import com.backend.user.entity.User;

import java.util.List;

public interface UserService {

    public List<UserDto> getAllUsers();
    public UserDto createUser(UserDto userDto);
    public UserDto getUSerById(Long id);
    public String deleteByUserId(Long id);
    public String savePostDataByUser(PostDto postDto, Long id);

}
