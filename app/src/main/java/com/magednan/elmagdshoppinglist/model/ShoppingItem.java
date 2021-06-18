package com.magednan.elmagdshoppinglist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.inject.Inject;

@Entity(tableName = "shopping")
public class ShoppingItem {
    @PrimaryKey(autoGenerate = true)
    private int item_id;
    private String type;
    private int amount;
    private String note;
    private String date;
@Inject
    public ShoppingItem( String type, int amount, String note, String date) {
        this.type = type;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    public ShoppingItem() {
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
