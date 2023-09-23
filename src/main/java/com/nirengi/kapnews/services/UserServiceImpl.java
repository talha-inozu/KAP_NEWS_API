package com.nirengi.kapnews.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nirengi.kapnews.data.entity.UserEntity;
import com.nirengi.kapnews.data.repository.UserRepository;
import com.nirengi.kapnews.dto.UserDto;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> responseList = new ArrayList<>();

        Iterable<UserEntity> entities = userRepository.findAll();

        for (UserEntity entity : entities) {
            responseList.add(entityToDto(entity));
        }
        return responseList;
    }

    @Override
    public List<String> getAllUsersEmails() {
        List<String> responseList = new ArrayList<>();

        Iterable<UserEntity> entities = userRepository.findAll();

        for (UserEntity entity : entities) {
            responseList.add(entity.getEmail());
        }
        return responseList;
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {

        List<UserDto> allUsers = getAllUsers();
        for (UserDto user : allUsers) {
            if (user.getEmail().equals(userDto.getEmail()))
                throw new RuntimeException("Email is already used !");
        }

        emailService.sendEmail("Welcome to KAPNEWSAPI !", userDto.getEmail());

        UserEntity userEntity = dtoToEntity(userDto);
        UserEntity responseEntity = userRepository.save(userEntity);
        UserDto responseDto = entityToDto(responseEntity);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exist !"));
        return ResponseEntity.ok(entityToDto(userEntity));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exist !"));
        Class<?> objectClass = userEntity.getClass();
        UserEntity newEntity = dtoToEntity(userDto);
        Field[] fields = objectClass.getDeclaredFields();


        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); // Allow access to private fields
            System.out.println(field.getName());

            try {
                field.set(userEntity, field.get(newEntity));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        UserEntity responseEntity = userRepository.save(userEntity);
        return ResponseEntity.ok(entityToDto(responseEntity));

    }

    @Override
    public ResponseEntity deleteUser(Long id) throws Throwable {
        try {
            Optional<UserEntity> userEntity = userRepository.findById(id);
            userRepository.delete(userEntity.get());
            return ResponseEntity.ok("User deleted");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public UserDto entityToDto(UserEntity userEntity) {

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserEntity dtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

}
