package com.nirengi.kapnews.controller;


import com.nirengi.kapnews.dto.UserDto;
import com.nirengi.kapnews.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserServiceController {

    @Autowired
    UserService userService;


    @GetMapping
    public List<UserDto> getAllUsers(){
        return  userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    public UserDto createUser(@RequestBody @Validated UserDto userDto){
        return  userService.createUser(userDto);
    }



}
