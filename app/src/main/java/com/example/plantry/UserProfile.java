package com.example.plantry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {
    TextView displayNameHead, displayNameLbl, emailLbl, ownerLbl;
    AppCompatButton changePw, logOut, contactUs;
    ImageView addHousehold, editHousehold;
    ArrayList<String> users;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference householdDB = database.getReference().child("household");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addHousehold = findViewById(R.id.add_household);
        editHousehold = findViewById(R.id.edit_household);
        displayNameHead = findViewById(R.id.displayName);
        displayNameLbl = findViewById(R.id.dash_displayName);
        emailLbl = findViewById(R.id.dash_email);
        ownerLbl = findViewById(R.id.dash_location);
        changePw = findViewById(R.id.change_pw);
        // TODO: Password change page and functionality
        logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        contactUs = findViewById(R.id.contact_us);
        contactUs.setOnClickListener(view ->{
            String mailto = "mailto:plantryapps@gmail.com" +
                            "?cc=" +
                            "&subject=" + Uri.encode("Plantry Support") +
                            "&body=" + Uri.encode("");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));

            try{
                startActivity(emailIntent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(UserProfile.this, "Error opening mail app", Toast.LENGTH_SHORT).show();
            }
        });

        displayUserData();
    }

    private void displayUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                householdDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(dataSnapshot.child("ownerUid").getValue().equals(profile.getUid())){
                                ownerLbl.setText(profile.getDisplayName());
                            }else if(dataSnapshot.child("membersUid").getValue().equals(profile.getUid())){
                                // TODO: set label for different household owner
                                // get the ownerUid, look up displayName of ownerUid and setLabel
                            }else{
                                Toast.makeText(UserProfile.this, "Database error, please contact us", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                // Name, email address
                String displayName = profile.getDisplayName();
                String email = profile.getEmail();

                displayNameHead.setText(displayName + "'s");
                displayNameLbl.setText(displayName);
                emailLbl.setText(email);
            }
        }
    }

    // TODO: add household member
    private void addHousehold(){

    }

    // TODO: edit household member
    private void editHousehold(){

    }

}