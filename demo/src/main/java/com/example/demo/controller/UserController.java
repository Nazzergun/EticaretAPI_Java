package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest) {
        return toResponse(userService.save(toDto(userRequest)));
    }

    public UserResponse toResponse(UserDto user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        return userResponse;

//        return UserResponse.builder()
//                .name(user.getName())
//                .surname(user.getSurname())
//                .id(user.getId())
//                .build();
    }

    public UserDto toDto(UserRequest userRequest) {
        UserDto userDto = new UserDto();
        userDto.setName(userRequest.getName());
        userDto.setSurname(userRequest.getSurname());
        return userDto;
    }
}
