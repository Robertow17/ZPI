package com.zpi.loginForm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.R;

public class LoginForm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setSignInButtonListener();
        setSignUpButtonListener();
    }

    private String getPassword() {
        TextView passwordInput = findViewById(R.id.passwordText);
        String password = passwordInput.getText().toString();

        return password;
    }

    private String getEmail() {
        TextView emailInput = findViewById(R.id.emailText);
        String email = emailInput.getText().toString();

        return email;
    }

    private String getUserType() {
         return getValueFromRadioButtonGroup(R.id.userTypeRadioGroup);
    }

    private String getUserSex() {
        return getValueFromRadioButtonGroup(R.id.userSexRadioGroup);
    }

    private String getValueFromRadioButtonGroup(int id) {
        RadioGroup radioGroup = findViewById(id);
        RadioButton chosenRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String chosenRadioButtonText = chosenRadioButton.getText().toString();

        return chosenRadioButtonText;
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

    private void setSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userType = getUserType();
                String userSex = getUserSex();
                String message = "User type: " + userType + "\nUser sex: " + userSex;

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}