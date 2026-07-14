package com.example.user.dto;

import com.example.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserDto {
    private final Long id;
    private final UserRole role;
    private final String userFullname;
    private final String userLogin;
    private final String phoneNumber;
    private final String email;
    private final String workplace;
}
