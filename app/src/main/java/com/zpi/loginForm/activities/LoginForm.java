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
import com.zpi.loginForm.infrastructure.FormValidator;
import com.zpi.loginForm.models.Sex;
import com.zpi.loginForm.models.UserModel;
import com.zpi.loginForm.models.UserType;
import com.zpi.loginForm.infrastructure.UserManager;

public class LoginForm extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        getLocalStorageIOPermission();
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
            boolean canUserBeSignedIn = UserManager.areCredentialsValid(getEmail(), getPassword());

            if (canUserBeSignedIn) goToMainPage();
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

            boolean canUserBeSignedUp = !UserManager.doesUserExist(getEmail());

            if (canUserBeSignedUp) {
                UserModel newUser = new UserModel(getEmail(), getPassword(), getUserSex(), getUserType());
                UserManager.registerUser(newUser);
                displaySuccessfulSignUpToast();
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

        if (radioButtonText.equals(engagedTypeButtonText)) return UserType.Engaged;
        else return UserType.ServiceProvider;
    }

    private Sex getUserSex() {
        String radioButtonText = getValueFromRadioButtonGroup(R.id.userSexRadioGroup);
        String femaleButtonText = getValueFromInput(R.id.femaleButton);

        if (radioButtonText.equals(femaleButtonText)) return Sex.Female;
        else return Sex.Male;
    }

    private String getValueFromRadioButtonGroup(int id) {
        RadioGroup radioGroup = findViewById(id);
        return getValueFromInput(radioGroup.getCheckedRadioButtonId());
    }
}
