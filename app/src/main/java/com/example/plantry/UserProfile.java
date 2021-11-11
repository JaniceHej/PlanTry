package com.example.plantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullName_p,email_p,phoneNo_p,password_p;
    TextView fullNameLabel_p,usernameLabel_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullName_p = findViewById(R.id.full_name_profile);
        email_p = findViewById(R.id.email_profile);
        phoneNo_p =  findViewById(R.id.phone_no_profile);
        password_p = findViewById(R.id.password_profile);
        fullNameLabel_p = findViewById(R.id.full_name_field_profile);
        usernameLabel_p = findViewById(R.id.username_field_profile);

        //ShowAllData
        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent=getIntent();
        String user_username=intent.getStringExtra("username");
        String user_name=intent.getStringExtra("name");
        String user_email=intent.getStringExtra("email");
        String user_phoneNo=intent.getStringExtra("phoneNo");
        String user_password=intent.getStringExtra("password");
        Toast.makeText(this, "name"+user_name, Toast.LENGTH_SHORT).show();
        fullNameLabel_p.setText(user_name);
        usernameLabel_p.setText(user_username);
        email_p.getEditText().setText(user_email);
        fullName_p.getEditText().setText(user_name);
        phoneNo_p.getEditText().setText(user_phoneNo);
        password_p.getEditText().setText(user_password);


    }
}