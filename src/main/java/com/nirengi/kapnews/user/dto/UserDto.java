package com.nirengi.kapnews.user.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class UserDto {

    private Long id;

    @NotNull
    @JsonView
    private String username;
    @NotNull
    @JsonView
    private String firstName;
    @NotNull
    @JsonView
    private String lastName;
    @NotNull
    @Email
    @JsonView
    private String email;
    @JsonView
    private String phoneNumber;

    @NotNull
    private String password;

    @JsonView
    private List<String> stockCodeList;

    @JsonView
    private List<String> patternList;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
