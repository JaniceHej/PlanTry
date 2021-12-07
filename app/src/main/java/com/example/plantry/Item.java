package com.example.plantry;

import static java.lang.Boolean.FALSE;

import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Item{
    private String itemName;
    private long expiryDate;
    private String addedBy;
    private Boolean isStaples;
    private Boolean isOnSale;

    // no arg constructor
    public Item() {
        this.itemName = "";
        this.expiryDate = -1;
        this.addedBy = "";
        this.isStaples = FALSE;
        this.isOnSale = FALSE;
    }

    // overloaded constructor
    public Item(String itemName, long expiryDate, String addedBy, Boolean isStaples, Boolean isOnSale) {
        this.itemName = itemName;
        this.expiryDate = expiryDate;
        this.addedBy = addedBy;
        this.isStaples = isStaples;
        this.isOnSale = isOnSale;
    }

    // setter and getter

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Boolean getStaples() {
        return isStaples;
    }

    public void setStaples(Boolean staples) {
        isStaples = staples;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }
}
