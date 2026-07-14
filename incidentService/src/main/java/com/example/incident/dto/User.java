package com.example.incident.dto;

import com.example.incident.enums.UserRole;
import lombok.*;

@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
@Getter
@ToString
public class User {
    private final Long id;
    private final UserRole role;
    private final String userFullname;
    private final String userLogin;
    private final String phoneNumber;
    private final String email;
    private final String workplace;
}
