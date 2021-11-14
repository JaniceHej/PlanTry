package com.example.plantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class WelcomeScreen extends AppCompatActivity{
    // variables
    AppCompatButton buyGroceries, managePantry, manageHousehold, findRecipes, logOut;
    TextView usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // hooks to all xml elements in activity_welcome.xml
        usernameLabel = findViewById(R.id.lbl_username);
        buyGroceries = findViewById(R.id.buy_groceries);
        buyGroceries.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        });
        managePantry = findViewById(R.id.manage_pantry);
        manageHousehold = findViewById(R.id.manage_household);
        findRecipes = findViewById(R.id.find_recipe);
        logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        getUsername();
    }

    private void getUsername(){
        Intent intent=getIntent();
        String usernameFromDB = intent.getStringExtra("username");
        usernameLabel.setText(usernameFromDB);
    }

}
