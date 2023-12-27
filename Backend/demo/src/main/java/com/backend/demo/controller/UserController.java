package com.backend.demo.controller;

import com.backend.demo.dto.PostDto;
import com.backend.demo.dto.UserDto;
import com.backend.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.demo.utils.Constants.*;

@RestController
@RequestMapping(BASE_URL)
@Slf4j
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
            log.info(String.valueOf(bindingResult));
            List<String> errors = bindingResult.getAllErrors().stream().map(error -> "Validation Error : " + error.getDefaultMessage()).collect(Collectors.toList());
            log.info(errors.toString());
            return ResponseEntity.badRequest().body(errors);
        }

        UserDto savedUser = userService.createUser(userDto);
        return ResponseEntity.ok(savedUser);
    }


    @PostMapping(USER_POST)
    public ResponseEntity<String> savePost(@Valid @RequestBody UserDto userDto) {
        String result = userService.saveUser(userDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping(UPDATE_POST)
    public String updateEmployee(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        System.out.println(userDto);
        return userService.updateUser(userDto, id);
    }

    @PostMapping("/{userId}/posts")
    public String savePostByUserId(@Valid @RequestBody PostDto postDto,@PathVariable Long userId){
        return userService.savePostByUserId(postDto,userId);
    }


}
