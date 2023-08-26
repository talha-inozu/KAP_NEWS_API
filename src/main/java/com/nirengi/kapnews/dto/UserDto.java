package com.nirengi.kapnews.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.fasterxml.jackson.annotation.JsonView;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class UserDto {

    private Long id;
    @JsonView
    private String username;
    @JsonView
    private String firstName;
    @JsonView
    private String lastName;
    @JsonView
    private String email;
    @JsonView
    private String phoneNumber;

}
