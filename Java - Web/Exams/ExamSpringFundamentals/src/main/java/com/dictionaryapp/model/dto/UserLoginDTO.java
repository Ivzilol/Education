package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 symbols")
    @NotNull
    private String username;

    @Size(min = 3, max = 20, message = "Password should be between 3 and 20 symbols")
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
