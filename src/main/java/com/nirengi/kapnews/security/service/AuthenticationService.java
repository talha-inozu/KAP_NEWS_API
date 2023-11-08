package com.nirengi.kapnews.security.service;

import com.nirengi.kapnews.security.dto.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> login(LoginDTO loginDTO);
}
