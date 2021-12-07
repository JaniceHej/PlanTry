package com.example.plantry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Calendar;

public class ItemsAdapter extends FirebaseRecyclerAdapter<Item, ItemsAdapter.ViewHolder>
{
    public ItemsAdapter(@NonNull FirebaseRecyclerOptions<Item> options){
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_card,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,int position, @NonNull Item item)
    {
        holder.name.setText(item.getItemName().toString());
        holder.addedBy.setText(item.getAddedBy().toString());

        // Calculate days to expired
        Calendar today = Calendar.getInstance();
        long diff = today.getTimeInMillis() - item.getExpiryDate();
        long days = diff / (24*60*60*1000);
        holder.expiryDate.setText(days + " days");
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,addedBy,expiryDate;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            addedBy = itemView.findViewById(R.id.added_by);
            expiryDate = itemView.findViewById(R.id.expiry_date);
        }
    }
}