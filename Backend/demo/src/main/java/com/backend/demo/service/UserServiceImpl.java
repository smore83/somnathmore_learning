package com.backend.demo.service;

import com.backend.demo.dto.PostDto;
import com.backend.demo.dto.UserDto;
import com.backend.demo.exception.UserNotFoundException;
import com.backend.demo.model.Post;
import com.backend.demo.model.User;
import com.backend.demo.repository.UserRepository;

import com.backend.demo.utils.Converters;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backend.demo.utils.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Converters converters;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> converters.entityToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = converters.dtoToEntity(userDto);
        User user1 = userRepository.save(user);

        return converters.entityToDto(user1);
    }

    @Transactional
    @Override
    public String saveUser(UserDto userDto) {
        User user = converters.dtoToEntity(userDto);

        for (Post post: user.getPostList()) {  //har project employye ke id set kara raha hai

            post.setUser(user); //ye to maintain kra raha hai id jane ke liye
        }
        userRepository.save(user);

        return DATA_SAVED;
    }

    @Transactional
    @Override
    public String updateUser(UserDto userDto, Long userId) {

        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUserName(userDto.getUserName());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setEmail(userDto.getEmail());
            List<Post> updatedPosts = new ArrayList<>();

            for (PostDto projectDto : userDto.getPostList()) {
                Post existingPost = existingUser.getPostList()
                        .stream()
                        .filter(p -> p.getId().equals(projectDto.getId()))
                        .findFirst()
                        .orElse(new Post()); // Create a new Post if not found

                existingPost.setTitle(projectDto.getTitle());
                existingPost.setDescription(projectDto.getDescription());
                //sab ayega idhar
                existingPost.setUser(existingUser); // Set the User association

                updatedPosts.add(existingPost);
            }

            existingUser.setPostList(updatedPosts);
            userRepository.save(existingUser);

            return UPDATED_SAVED;
        }

        throw new UserNotFoundException(NOT_FOUND);
    }

    @Override
    public String savePostByUserId(PostDto postDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(NOT_FOUND);
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUser(userOptional.get());
        userOptional.get().getPostList().add(post);
        userRepository.save(userOptional.get());
        return POST_SAVED;
    }
}
