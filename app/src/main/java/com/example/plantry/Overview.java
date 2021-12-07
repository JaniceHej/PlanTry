package com.example.plantry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Overview extends AppCompatActivity {
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Recycler mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    CardView fruits, dryGoods, seafoods, vegetables, meats, dairy;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // TODO: CHANGE intent views
        fruits = findViewById(R.id.category_fruits);
        fruits.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Overview.class);
            startActivity(intent);        });
        dryGoods = findViewById(R.id.category_drygoods);
        dryGoods.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Overview.class);
            startActivity(intent);
        });
        seafoods = findViewById(R.id.category_seafoods);
        seafoods.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Overview.class);
            startActivity(intent);
        });
        vegetables = findViewById(R.id.category_vegetables);
        vegetables.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Overview.class);
            startActivity(intent);
        });
        meats = findViewById(R.id.category_meats);
        meats.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Overview.class);
            startActivity(intent);
        });
        dairy = findViewById(R.id.category_dairy);
        dairy.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), DairyList.class);
            startActivity(intent);
        });




        // add new pantryList items by creating new PantryList instance
        //ArrayList<Categories> categories = new ArrayList<>();
        //categories.add(new Categories("","","Produce"));
/*        ArrayList<String> categories = new ArrayList<>();
        categories.add("Produce");
        categories.add("Fruits");
        categories.add("Dairy");*/
    }
}