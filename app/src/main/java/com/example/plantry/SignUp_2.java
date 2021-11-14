package com.example.plantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_2 extends AppCompatActivity {
    //Variables
    TextInputLayout regUsername;
    AppCompatButton regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    CharSequence regMessage = "Registration successful. Please login";
    int duration = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        //Hooks to all xml elements in activity_sign_up.xml
        regUsername = findViewById(R.id.username);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        regToLoginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace="\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        }
        else
        {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    // obtain username, register, and prompt user to login
    public void registerUser(View view) {
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");
        if (!validateUsername()){
            return;
        }

        // get all the values in String
        Intent intent = getIntent();
        String regEmail = intent.getStringExtra("email");
        String regPassword = intent.getStringExtra("password");
        String username = regUsername.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(username, regEmail, regPassword);
        // reference.setValue(helperClass);
        reference.child(username).setValue(helperClass);

        // prompt user to login after successful registration
        Toast toast = Toast.makeText(getApplicationContext(), regMessage, duration);
        toast.show();

        Intent intent1 = new Intent(getApplicationContext(), Login.class);
        startActivity(intent1);
    }
}