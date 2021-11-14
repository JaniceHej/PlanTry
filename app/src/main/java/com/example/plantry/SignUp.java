package com.example.plantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regEmail, regPassword;
    AppCompatButton nextBtn, regToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks to all xml elements in activity_sign_up.xml
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        nextBtn = findViewById(R.id.next_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        regToLoginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal="^"+
                //"(?=.*[0-9])"+ // at least 1 digit
                //"(?=.*[a-z])"+  // at least 1 lower case letter
                //"(?=.*[A-Z])"+   // at least 1 upper case letter
               "(?=.*[a-zA-Z])"+   // any letter
                "(?=.*[@#$%^&+=])" + // at least 1 special character
                "(?=\\S+$)" + // no white spaces
                ".{4,}"+    // at 4 least  characters
                "$" ;
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal)){
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    // prompt user to next view (input username)
    public void registerUserEmailPass(View view) {
        if (!validateEmail() | !validatePassword()){
            return;
        }

        //Get all the values in String
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), SignUp_2.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}