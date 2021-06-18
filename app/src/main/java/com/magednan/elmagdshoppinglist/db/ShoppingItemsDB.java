package com.magednan.elmagdshoppinglist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.magednan.elmagdshoppinglist.model.ShoppingItem;

@Database(entities = ShoppingItem.class, version = 1, exportSchema = false)
public abstract class ShoppingItemsDB extends RoomDatabase {
    //This is Db class
    public abstract ShoppingDao shoppingDao();
}
