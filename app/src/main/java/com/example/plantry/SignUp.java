package com.example.plantry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regEmail, regPassword;
    AppCompatButton nextBtn, regToLoginBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks to all xml elements in activity_sign_up.xml
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        nextBtn = findViewById(R.id.next_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        mAuth = FirebaseAuth.getInstance();
        nextBtn.setOnClickListener(view -> {
            createUser();
        });
        regToLoginBtn.setOnClickListener(view -> {
         startActivity(new Intent(SignUp.this,Login.class));
        });
    }
    private void createUser(){
        String email=regEmail.getEditText().getText().toString();
        String password=regPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty(email)){
            regEmail.setError("Email cannot be empty");
            regEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            regPassword.setError("Password cannot be empty");
            regPassword.requestFocus();

        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(SignUp.this,Login.class));
                    }else{
                        Toast.makeText(SignUp.this, "Registration Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

