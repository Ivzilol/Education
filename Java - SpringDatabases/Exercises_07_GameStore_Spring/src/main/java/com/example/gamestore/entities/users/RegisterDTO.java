package com.example.gamestore.entities.users;


import com.example.gamestore.exeptions.ValidationException;

/*
    Validate the data for registering a user.
    commandParts[0] is skipped because it contains the command name
    witch is not needed for user object
    @param commandParts all date read form the console

     */
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void validate() {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");

        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot){
            throw new ValidationException("Email must contain @ and.");
        }
        if (!password.equals(confirmPassword)){
            throw new ValidationException("Password and Confirm password must match.");
        }
    }
}
