package com.backend.user.controller;

import com.backend.demo.model.Post;
import com.backend.user.dto.PostDto;
import com.backend.user.dto.UserDto;
import com.backend.user.entity.User;
import com.backend.user.service.UserService;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.user.utils.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(objectError -> "Validation Error : " + objectError.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        UserDto userDto1 = userService.createUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public UserDto getUSerById(@PathVariable Long id) {
        return userService.getUSerById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteByUserId(@PathVariable Long id) {
        return userService.deleteByUserId(id);
    }


    @PostMapping("{id}/posts")
    public ResponseEntity<?> savePostDataByUser(@RequestBody PostDto postDto, @PathVariable Long id,BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(objectError -> "Validation Error : " + objectError.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        String userDto1 = userService.savePostDataByUser(postDto,id);

        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
}
