package com.example.BattleShips.model.dto;

import com.example.BattleShips.validation.annotation.UniqueEmail;
import com.example.BattleShips.validation.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDTO {
    @UniqueUsername
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters")
    @NotNull
    private String username;
    @Size(min = 5, max = 20, message = "Full Name length must be between 5 and 20 characters")
    @NotNull
    private String fullName;
    @Size(min = 3, message = "Password length must be more from 3 characters")
    @NotNull
    private String password;
    @UniqueEmail
    @Email(message = "Enter valid email address")
    @NotNull
    private String email;
    @NotNull
    @Size(min = 3, message = "Password length must be more from 3 characters")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
