package com.example.gamestore.services;

public interface ExecuteService {

    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    String LOGOUT_USER_COMMAND = "LogoutUser";

    String ADD_GAME_COMMAND = "AddGame";
    String execute(String command, String data);

    String REGISTER_USER_JSON = """
            {
            "commandName": "RegisterUser",
            "email": "pesho22@mail.com",
            "password", "1234567"
            "confirmPassword": "1234567"
            "fillName": "Pesho 22334"
            }
            """;
    String LOGIN_USER_JSON = """
            {
            "commandName": "RegisterUser",
            "email": "pesho22@mail.com",
            }
            """;
}
