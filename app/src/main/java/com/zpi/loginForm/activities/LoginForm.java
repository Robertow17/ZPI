package com.zpi.loginForm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    private void setSignInButtonListener() {
        Button signUpButton = findViewById(R.id.signInButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = getPassword();
                Toast toast = Toast.makeText(getApplicationContext(), "Password: " + password, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void setSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = getPassword();
                Toast toast = Toast.makeText(getApplicationContext(), "Password: " + password, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}