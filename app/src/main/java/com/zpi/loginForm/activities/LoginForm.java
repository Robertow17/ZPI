package com.zpi.loginForm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.loginForm.models.Sex;
import com.zpi.loginForm.models.UserType;

public class LoginForm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setSignInButtonListener();
        setSignUpButtonListener();
    }

    private void setSignInButtonListener() {
        Button signUpButton = findViewById(R.id.signInButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getEmail();
                String password = getPassword();
                String message = "Email: " + email + "\nPassword: " + password;

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
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

    private void setSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserType userType = getUserType();
                Sex sex = getUserSex();
                String message = "User type: " + userType + "\nUser sex: " + sex;

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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