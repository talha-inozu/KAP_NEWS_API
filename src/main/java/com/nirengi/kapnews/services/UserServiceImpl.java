package com.nirengi.kapnews.services;


import com.nirengi.kapnews.data.entity.UserEntity;
import com.nirengi.kapnews.data.repository.UserRepository;
import com.nirengi.kapnews.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> responseList = new ArrayList<>();

        Iterable<UserEntity> entities = userRepository.findAll();

        for(UserEntity entity:entities){
            responseList.add(entityToDto(entity));
        }
        return responseList ;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = dtoToEntity(userDto);
        UserEntity responseEntity = userRepository.save(userEntity);
        UserDto responseDto = entityToDto(responseEntity);
        return  responseDto;
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) throws Throwable {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) throws Throwable {
        return null;
    }

    @Override
    public UserDto entityToDto(UserEntity userEntity) {

        UserDto userDto = modelMapper.map(userEntity,UserDto.class);
        return  userDto;
    }

    @Override
    public UserEntity dtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        return  userEntity;
    }
}
