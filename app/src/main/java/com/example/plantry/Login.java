package com.example.plantry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignUp,btnLogin;
    TextInputLayout email, password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callSignUp = findViewById(R.id.signup_screen);
        btnLogin = findViewById(R.id.login_go_btn);
        email = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        mAuth= FirebaseAuth.getInstance();
    btnLogin.setOnClickListener(view->{
        loginUser();
    });
    callSignUp.setOnClickListener(view ->{
        startActivity(new Intent(Login.this,SignUp.class));
    });
    }
    private void loginUser(){
        String lemail=email.getEditText().getText().toString();
        String lpassword=password.getEditText().getText().toString();
        if (TextUtils.isEmpty(lemail)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(lpassword)){
            password.setError("Password cannot be empty");
            password.requestFocus();

        }else{
            mAuth.signInWithEmailAndPassword(lemail,lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(Login.this,WelcomeScreen.class));
               }else{
                        Toast.makeText(Login.this, "Login Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    }

