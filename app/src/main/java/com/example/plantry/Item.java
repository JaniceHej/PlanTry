package com.example.plantry;

import static java.lang.Boolean.FALSE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Item extends PantryList{
    private String itemName;
    private Date expiryDate;
    private String addedBy;
    private Boolean isStaples;
    private Boolean isOnSale;

    // no arg constructor
    public Item() {
        super("", "", new ArrayList<String>(), new ArrayList<PantryList>(), new ArrayList<Item>(), new ArrayList<Item>());
        this.itemName = "";
        this.expiryDate = new Date();
        this.addedBy = "";
        this.isStaples = FALSE;
        this.isOnSale = FALSE;
    }

    // overloaded constructor
    public Item(String ownerUid, String ownerEmail, ArrayList<String> membersUid, ArrayList<PantryList> list, ArrayList<Item> shoppingList, ArrayList<Item> item, String itemName, Date expiryDate, String addedBy, Boolean isStaples, Boolean isOnSale) {
        super(ownerUid, ownerEmail, membersUid, list, shoppingList, item);
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
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
