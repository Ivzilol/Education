package com.example.gamestore.exeptions;

public class UserNotLoggedInExeption extends RuntimeException {
    public UserNotLoggedInExeption() {
        super("Execute Login command first!");
    }
}
