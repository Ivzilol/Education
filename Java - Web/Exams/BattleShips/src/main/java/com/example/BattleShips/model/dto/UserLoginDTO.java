package com.example.BattleShips.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 3, max = 20, message = "Username should be between 3 and 10 symbols")
    @NotNull
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be more than 3 characters")
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
