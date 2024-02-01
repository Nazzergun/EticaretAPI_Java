package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /*
    DB -> Entity
    Service -> Dto
    Controller -> request girecek response çıkacak
     */
    @Override
    public UserDto save(UserDto userDto) {
        User user = toEntity(userDto);
        user = userRepository.save(user);
        return toDto(user);
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }



    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        return user;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;

//        return UserDto.builder()
//            .id(user.getId())
//            .name(user.getName())
//            .surname(user.getSurname())
//            .build();
    }
}
