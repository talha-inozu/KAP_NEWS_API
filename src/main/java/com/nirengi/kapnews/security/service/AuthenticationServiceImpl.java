package com.nirengi.kapnews.security.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.nirengi.kapnews.security.JwtUtil;
import com.nirengi.kapnews.security.dto.LoginDTO;
import com.nirengi.kapnews.user.dto.UserDto;
import com.nirengi.kapnews.user.service.UserService;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationProvider authenticationProvider;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        try {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            UserDto userDto = userService.getUserByUsername(loginDTO.getUsername()).getBody();
            String token = jwtUtil.createToken(userDto);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Credentials!");
        }
    }
}
