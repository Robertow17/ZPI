package com.zpi.loginForm.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String email;
    private String password;
    private Sex sex;
    private UserType userType;

    public UserModel(String email, String password, Sex sex, UserType userType) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Sex getSex() {
        return sex;
    }

    public UserType getUserType() {
        return userType;
    }
}
