package com.nirengi.kapnews.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nirengi.kapnews.security.JwtUtil;
import com.nirengi.kapnews.user.dto.UserDto;
import com.nirengi.kapnews.user.service.UserService;

@Component
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        String accessToken = jwtUtil.resolveToken(request);
        if (accessToken == null) {
            //filterChain.doFilter(request, response);
            //byPass
            Authentication authentication = new UsernamePasswordAuthenticationToken("userDto", null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            //byPass
            return;
        }
        Claims claims = jwtUtil.resolveClaims(request);

        if (claims != null & jwtUtil.validateClaims(claims)) {
            String username = claims.get("username").toString();
            UserDto userDto = userService.getUserByUsername(username).getBody();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDto, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
