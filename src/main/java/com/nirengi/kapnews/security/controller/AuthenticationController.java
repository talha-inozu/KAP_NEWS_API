package com.nirengi.kapnews.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nirengi.kapnews.security.dto.LoginDTO;
import com.nirengi.kapnews.security.service.AuthenticationService;
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return authenticationService.login(loginDTO);
    }


}
