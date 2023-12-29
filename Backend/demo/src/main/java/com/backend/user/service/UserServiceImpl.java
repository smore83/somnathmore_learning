package com.backend.user.service;


import com.backend.user.dto.PostDto;
import com.backend.user.dto.UserDto;
import com.backend.user.entity.Post;
import com.backend.user.entity.User;
import com.backend.user.exceptions.UserNotFoundException;
import com.backend.user.repository.UserRepository;
import com.backend.user.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Converters converters;
    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=userRepository.findAll();
        return userList.stream().map(user -> converters.entityToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=converters.dtoToEntity(userDto);
        User user1=userRepository.save(user);
        return converters.entityToDto(user1);
    }

    @Override
    public UserDto getUSerById(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Given Id Not Found");
        }
        return converters.entityToDto(userOptional.get());
    }

    @Override
    public String deleteByUserId(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Given Id Not Found");
        }
        userRepository.deleteById(id);
        return "Success Delete";

    }

    @Override
    public String savePostDataByUser(PostDto postDto, Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Given Id Not Found");
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUser(userOptional.get());

        userOptional.get().getPostList().add(post);
        userRepository.save(userOptional.get());

        return "Saved Post Data";
    }
}
