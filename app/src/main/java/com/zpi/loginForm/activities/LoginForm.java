package com.zpi.loginForm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.FileManager.FileManager;
import com.zpi.MainActivity;
import com.zpi.R;
import com.zpi.loginForm.models.Sex;
import com.zpi.loginForm.models.UserModel;
import com.zpi.loginForm.models.UserType;

import java.util.List;

public class LoginForm extends AppCompatActivity {
    FileManager<UserModel> fileManager = new FileManager<>("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setSignInButtonListener();
        setSignUpButtonListener();
    }

    private void setSignInButtonListener() {
        Button signUpButton = findViewById(R.id.signInButton);
        signUpButton.setOnClickListener(view -> {
            List<UserModel> currentUsers = fileManager.getFromFile();
            boolean canUserBeSignIn = areCredentialsValid(currentUsers, getEmail(), getPassword());

            if(canUserBeSignIn) goToMainPage();
            else displayUnsuccessfulSignInToast();
        });
    }

    private String getPassword() {
        return getValueFromInput(R.id.passwordText);
    }

    private String getEmail() {
        return getValueFromInput(R.id.emailText);
    }

    private String getValueFromInput(int inputId) {
        TextView input = findViewById(inputId);

        return input.getText().toString();
    }

    private void goToMainPage() {
        Intent intent = new Intent(LoginForm.this, MainActivity.class);
        startActivity(intent);
    }

    private void displayUnsuccessfulSignInToast() {
        makeToast("Nie udało się zalogować!");
    }

    private void makeToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void setSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(view -> {
            List<UserModel> currentUsers = fileManager.getFromFile();
            boolean canUserBeSignedUp = !doesUserExist(currentUsers, getEmail());

            if(canUserBeSignedUp) {
                UserModel newUser = new UserModel(getEmail(), getPassword(), getUserSex(), getUserType());
                registerUser(currentUsers, newUser);
                displaySuccessfulSignUpToast();
            }
            else displayUnsuccessfulSignUpToast();
        });
    }

    private boolean doesUserExist(List<UserModel> users, String email) {
        return users.parallelStream().anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean areCredentialsValid(List<UserModel> users, String email, String password) {
        return users.parallelStream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    private void registerUser(List<UserModel> currentUsers, UserModel newUser) {
        currentUsers.add(newUser);
        fileManager.saveToFile(currentUsers);
    }

    private void displaySuccessfulSignUpToast() {
        makeToast("Pomyślnie zarejestrowano!");
    }

    private void displayUnsuccessfulSignUpToast() {
        makeToast("Nie udało się zarejestrować. Spróbuj później, a jeśli posiadasz konto - zaloguj się!");
    }

    private UserType getUserType() {
        String radioButtonText = getValueFromRadioButtonGroup(R.id.userTypeRadioGroup);
        String engagedTypeButtonText = getValueFromInput(R.id.engagedUserButton);

        if(radioButtonText.equals(engagedTypeButtonText)) return UserType.Engaged;
        else return UserType.ServiceProvider;
    }

    private Sex getUserSex() {
        String radioButtonText = getValueFromRadioButtonGroup(R.id.userSexRadioGroup);
        String femaleButtonText = getValueFromInput(R.id.femaleButton);

        if(radioButtonText.equals(femaleButtonText)) return Sex.Female;
        else return Sex.Male;
    }

    private String getValueFromRadioButtonGroup(int id) {
        RadioGroup radioGroup = findViewById(id);

        return getValueFromInput(radioGroup.getCheckedRadioButtonId());
    }
}
