package com.nirengi.kapnews.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nirengi.kapnews.data.entity.UserEntity;
import com.nirengi.kapnews.dto.UserDto;

public interface UserService {

    public List<UserDto> getAllUsers();

    public List<String> getAllUsersEmails();

    public ResponseEntity<UserDto> createUser(UserDto userDto);

    public ResponseEntity<UserDto> getUserById(Long id);

    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) throws Throwable;

    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) throws Throwable;

    //Model Wrapper
    public UserDto entityToDto(UserEntity userEntity);

    public UserEntity dtoToEntity(UserDto userDto);


}
