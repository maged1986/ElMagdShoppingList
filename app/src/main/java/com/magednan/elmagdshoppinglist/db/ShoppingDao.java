package com.magednan.elmagdshoppinglist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.magednan.elmagdshoppinglist.model.ShoppingItem;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ShoppingDao {
    //this is dao interface
    @Insert(onConflict = IGNORE)
    public void insertItem(ShoppingItem item);

    @Query("delete from  shopping where item_id =:item_id")
    public void deleteItem(String item_id);

    @Query("DELETE FROM shopping")
    public void deleteAll( );

    @Query("select * from shopping")
    public LiveData<List<ShoppingItem>> getItems();
}
