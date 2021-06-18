package com.magednan.elmagdshoppinglist.viewmodes;

import android.content.Context;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.magednan.elmagdshoppinglist.db.ShoppingDao;
import com.magednan.elmagdshoppinglist.model.ShoppingItem;
import com.magednan.elmagdshoppinglist.repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MainRepository repository;
    ShoppingDao shoppingDao;

    @ViewModelInject
    public MainViewModel(MainRepository repository) {
        this.repository = repository;
    }
    public void uploadItem(ShoppingItem item, Context context) {
        repository.uploadItem(item, context);
    }


    public void deleteItem(String item_id) {
        repository.deleteItem(item_id);
    }

    public void deleteAll( ){
        repository.deleteAll();
    }

    public LiveData<List<ShoppingItem>> getItemsDB(){
        return repository.getItemsDB();
    }
    public void getItems() {repository.getItems();}
    public int getPrice(List<ShoppingItem> list){return repository.getPrice(list);}




}
