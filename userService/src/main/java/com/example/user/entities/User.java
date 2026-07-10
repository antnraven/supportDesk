package com.example.user.entities;

import com.example.user.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserRole role;
    private String userFullname;
    private String userLogin;
    private String phoneNumber;
    private String email;
    private String workplace;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", userFullname='" + userFullname + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", workplace='" + workplace + '\'' +
                '}';
    }
}
