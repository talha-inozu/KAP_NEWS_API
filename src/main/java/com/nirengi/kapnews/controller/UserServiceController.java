package com.nirengi.kapnews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nirengi.kapnews.dto.UserDto;
import com.nirengi.kapnews.services.EmailServiceImpl;
import com.nirengi.kapnews.services.UserService;

@RestController
@RequestMapping("/users")
public class UserServiceController {

    @Autowired
    UserService userService;
    @Autowired
    EmailServiceImpl emailService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    public UserDto createUser(@RequestBody @Validated UserDto userDto) {
        return userService.createUser(userDto).getBody();
    }

    @PostMapping(value = "/update/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Validated UserDto userDto) throws Throwable {
        return userService.updateUser(id, userDto).getBody();
    }

    @PostMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable Long id) throws Throwable {
        return userService.getUserById(id).getBody();
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) throws Throwable {
        return userService.deleteUser(id);
    }
}
