package com.example.plantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class UserProfile extends AppCompatActivity {
    TextView displayNameHead, displayNameLbl, emailLbl, locationLbl;
    AppCompatButton logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayNameHead = findViewById(R.id.displayName);
        displayNameLbl = findViewById(R.id.dash_displayName);
        emailLbl = findViewById(R.id.dash_email);
        locationLbl = findViewById(R.id.dash_location);
        logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        //ShowAllData
        displayUserData();
    }

    private void displayUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Name, email address
                String displayName = profile.getDisplayName();
                String email = profile.getEmail();
                // TODO: get user's location

                displayNameHead.setText(displayName + "'s");
                displayNameLbl.setText(displayName);
                emailLbl.setText(email);
                // TODO: set location label
            }
        }
    }

    // TODO: contact us
}