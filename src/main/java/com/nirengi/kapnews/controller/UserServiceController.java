package com.nirengi.kapnews.controller;


import com.nirengi.kapnews.dto.UserDto;
import com.nirengi.kapnews.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<UserDto> getAllUsers(){
        return  userService.getAllUsers();
    }

    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public UserDto createUser(@RequestBody @Validated UserDto userDto){
        return  userService.createUser(userDto);
    }



}
