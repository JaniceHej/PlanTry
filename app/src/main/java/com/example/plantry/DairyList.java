package com.example.plantry;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DairyList extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemsAdapter items;
    TextView categoryHeading;
    EditText name;
    DatePicker expiryDate;
    Boolean isStaples;
    ImageButton addItem, searchItem, editItem;
    AppCompatButton addItemButton, cancelButton;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference householdDB = database.getReference().child("household");
    private DatabaseReference userDB = database.getReference().child("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        categoryHeading = findViewById(R.id.category_name);
        categoryHeading.setText("Dairy");
        addItem = findViewById(R.id.add_item);
        addItem.setOnClickListener(View ->{
            showAddItem();
        });
        recyclerView=findViewById(R.id.items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: show list items @ recycler view
        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(householdDB.getRef(), Item.class).build();

        items = new ItemsAdapter(options);
        recyclerView.setAdapter(items);

      /*  final Boolean[] isShown = {Boolean.FALSE};
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null && !isShown[0]){
            for (UserInfo profile : user.getProviderData()){
                // loop through database to find user's profile, obtain household owner email
                userDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(dataSnapshot.child("email").getValue().equals(profile.getEmail())){
                                String owner = dataSnapshot.child("householdOwner").getValue().toString();

                                // loop through database to find household
                                householdDB.orderByChild("ownerEmail").equalTo(owner).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            for(DataSnapshot data : snapshot.getChildren()){
                                               String key = data.getKey();

                                               FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(householdDB.child(key).child("dairy").getRef(), Item.class).build();

                                               items = new ItemsAdapter(options);
                                               recyclerView.setAdapter(items);
                                               isShown[0] = true;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(DairyList.this, "Add item failed. Please try again", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }*/
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        items.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        items.stopListening();
    }

    private void showAddItem(){
        dialogBuilder = new AlertDialog.Builder((this));
        final View addItemPopupView = getLayoutInflater().inflate(R.layout.popup_additem, null);

        addItemButton = addItemPopupView.findViewById(R.id.add_item);
        cancelButton = addItemPopupView.findViewById(R.id.cancel_addItem);

        dialogBuilder.setView(addItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        // perform add items to DB
        addItemButton.setOnClickListener(new View.OnClickListener(){

        // flag to solve double entry problem
        Boolean isAdded = Boolean.FALSE;

            @Override
            public void onClick(View v){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    for (UserInfo profile : user.getProviderData()){
                        String displayName = profile.getDisplayName();
                        name = (EditText) addItemPopupView.findViewById(R.id.name_addItem);
                        String productName = name.getEditableText().toString();
                        expiryDate = (DatePicker) addItemPopupView.findViewById(R.id.picker_expiry_date);
                        int day = expiryDate.getDayOfMonth();
                        int month = expiryDate.getMonth() + 1;
                        int year = expiryDate.getYear();

                        Calendar calendar = new GregorianCalendar(year, month, day);
                        long expiration = calendar.getTimeInMillis();

                        // loop through database to find user's profile, obtain household owner email
                        userDB.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    if(dataSnapshot.child("email").getValue().equals(profile.getEmail())){
                                        String owner = dataSnapshot.child("householdOwner").getValue().toString();

                                        // loop through database to find household
                                        householdDB.orderByChild("ownerEmail").equalTo(owner).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    for(DataSnapshot data : snapshot.getChildren()){
                                                        String key = data.getKey();
                                                        Item dairyItem = new Item(productName, expiration, displayName, isStaples, false);

                                                        if(!isAdded){
                                                            householdDB.child(key).child("dairy").child(productName).setValue(dairyItem);
                                                            Toast.makeText(DairyList.this, productName + " added successfully", Toast.LENGTH_SHORT).show();
                                                            isAdded = Boolean.TRUE;
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(DairyList.this, "Add item failed. Please try again", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }
                                isAdded = Boolean.FALSE;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    // TODO: Display expiration date - today
    // Calendar today = Calendar.getInstance()
    // long diff = today.getTimeInMillis() - expiration
    // long days = diff / (24*60*60*1000)

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_staples:
                if(checked)
                    isStaples = Boolean.TRUE;
                else
                    isStaples = Boolean.FALSE;
        }
    }
}
