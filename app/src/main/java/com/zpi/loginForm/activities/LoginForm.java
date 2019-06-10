package com.zpi.loginForm.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.MainActivity;
import com.zpi.R;
import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.loginForm.infrastructure.FormValidator;
import com.zpi.model.User;
import com.zpi.model.enums.Gender;
import com.zpi.model.enums.UserType;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;

import java.util.List;

public class LoginForm extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MEMORY_ACCESS = 5;
    List<User> users;
    ServerConnector<User> utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        getLocalStorageIOPermission();

        utils = new ServerConnector(ServiceName.users);
        users = utils.getAll();

        System.out.print("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean isPermissionGranted =
                requestCode == MEMORY_ACCESS &&
                        grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED;

        if (isPermissionGranted) {
            setSignInButtonListener();
            setSignUpButtonListener();
        }
    }

    private void getLocalStorageIOPermission() {
        boolean hasPermissionAlreadyBeenGranted = ActivityCompat.shouldShowRequestPermissionRationale(LoginForm.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasPermissionAlreadyBeenGranted) {
            ActivityCompat.requestPermissions(LoginForm.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }
    }

    private void setSignInButtonListener() {
        Button signUpButton = findViewById(R.id.signInButton);
        signUpButton.setOnClickListener(view -> {
            boolean canUserBeSignedIn = users.parallelStream().anyMatch(user -> user.getLogin().equals(getEmail()) && user.getPassword().equals(getPassword()));

            User current = getUser(getEmail());
            if (canUserBeSignedIn) goToMainPage(current);
            else displayUnsuccessfulSignInToast();
        });
    }

    private User getUser(String login){
        for( User u : users){
            if(u.getLogin().equals(login)){
                return u;
            }
        }
        return null;
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

    private void goToMainPage(User current) {
        if(current.getType()== com.zpi.model.enums.UserType.SERVICE_PROVIDER){
            Intent intent = new Intent(LoginForm.this, ServiceProviderMainActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(LoginForm.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void displayUnsuccessfulSignInToast() {
        makeToast("Nie udało się zalogować!");
    }

    private void makeToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setGoToSignUpListener(View view) {
        LinearLayout signUpForm = findViewById(R.id.signUpForm);
        signUpForm.setVisibility(View.VISIBLE);
    }

    private void setSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(view -> {
            if(!areCredentialsValid(getEmail(), getPassword())) return;

            boolean canUserBeSignedUp = !users.parallelStream().anyMatch(user -> user.getLogin().equals(getEmail()));

            if (canUserBeSignedUp) {
                User newUser = new User(getEmail(), getPassword(), getUserSex(), getUserType());
                utils.add(newUser);
                displaySuccessfulSignUpToast();
                Intent intent = new Intent(LoginForm.this, LoginForm.class);
                startActivity(intent);
            } else displayUnsuccessfulSignUpToast();
        });
    }

    private boolean areCredentialsValid(String email, String password) {
        if (!FormValidator.isValidEmail(email)) {
            makeToast("Podany email jest niepoprawny.");
            return false;
        }

        if (!FormValidator.isValidPassword(password)) {
            makeToast("Hasło nie może być krótsze niż 6 znaków oraz powinno posiadać cyfrę.");
            return false;
        }

        return true;
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

        if (radioButtonText.equals(engagedTypeButtonText)) return UserType.USER;
        else return UserType.SERVICE_PROVIDER;
    }

    private Gender getUserSex() {
        String radioButtonText = getValueFromRadioButtonGroup(R.id.userSexRadioGroup);
        String femaleButtonText = getValueFromInput(R.id.femaleButton);

        if (radioButtonText.equals(femaleButtonText)) return Gender.FEMALE;
        else return Gender.MALE;
    }

    private String getValueFromRadioButtonGroup(int id) {
        RadioGroup radioGroup = findViewById(id);
        return getValueFromInput(radioGroup.getCheckedRadioButtonId());
    }
}
