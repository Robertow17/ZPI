package com.zpi.loginForm.infrastructure;

import com.zpi.FileManager.FileManager;
import com.zpi.loginForm.models.UserModel;

import java.util.List;

public class UserManager {
    private static FileManager<UserModel> fileManager = new FileManager<>("users");

    public static boolean areCredentialsValid(String email, String password) {
        List<UserModel> users = fileManager.getFromFile();
        return users.parallelStream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public static boolean doesUserExist(String email) {
        List<UserModel> users = fileManager.getFromFile();
        return users.parallelStream().anyMatch(user -> user.getEmail().equals(email));
    }

    public static void registerUser(UserModel newUser) {
        List<UserModel> currentUsers = fileManager.getFromFile();
        currentUsers.add(newUser);
        fileManager.saveToFile(currentUsers);
    }
}
