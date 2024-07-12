package com.example.piggytech.DTO;

public class LoginRequest {
    private String usernameOrEmail;
    private String password;

    LoginRequest() {}

    //constructors
    public LoginRequest(String usernameOrEmail, String password){
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    //Setters
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }
    public String getPassword() {
        return password;
    }

}
