package com.nirengi.kapnews.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nirengi.kapnews.user.dto.UserEntity;
import com.nirengi.kapnews.user.dto.UserDto;

public interface UserService {

    public List<UserDto> getAllUsers();

    public List<String> getAllUsersEmails();

    public ResponseEntity<UserDto> registerUser(UserDto userDto);

    public ResponseEntity<UserDto> getUserById(Long id);

    public ResponseEntity<UserDto> getUserByUsername(String username);


    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) throws Throwable;

    public ResponseEntity deleteUser(Long id) throws Throwable;

    //Model Wrapper
    public UserDto entityToDto(UserEntity userEntity);

    public UserEntity dtoToEntity(UserDto userDto);


}
