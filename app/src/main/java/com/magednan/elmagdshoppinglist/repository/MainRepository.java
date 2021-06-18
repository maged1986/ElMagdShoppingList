package com.magednan.elmagdshoppinglist.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.magednan.elmagdshoppinglist.db.ShoppingDao;
import com.magednan.elmagdshoppinglist.firebase.FireBaseAuth;
import com.magednan.elmagdshoppinglist.firebase.FireBaseDataManager;
import com.magednan.elmagdshoppinglist.model.ShoppingItem;

import java.util.List;

import javax.inject.Inject;

public class MainRepository {
    private ShoppingDao shoppingDao;
    private FireBaseDataManager dataManager;

    @Inject
    public MainRepository(ShoppingDao shoppingDao, FireBaseDataManager dataManager) {
        this.shoppingDao = shoppingDao;
        this.dataManager = dataManager;
    }

    public void uploadItem(ShoppingItem item, Context context) {
        dataManager.uploadItem(item, context);
    }
    public void getItems() {
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        assert firebaseAuth != null;
        db.collection(userid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                for (DocumentSnapshot ds : value.getDocuments()) {
                    ShoppingItem item = ds.toObject(ShoppingItem.class);
                    Log.d("klk","vent:E "+ item);
                    shoppingDao.insertItem(item);
                }
            }
        });}

        public int getPrice(List<ShoppingItem> list){
        int price=0;
        if (list.size() >=1){
        for (ShoppingItem item:list){
            price=price+item.getAmount();
        }}return price;
        }

    public void insertItem(ShoppingItem item) {
        shoppingDao.insertItem(item);
    }

    public void deleteItem(String item_id) {
        shoppingDao.deleteItem(item_id);
    }

    public void deleteAll( ){
        shoppingDao.deleteAll();
    }

    public LiveData<List<ShoppingItem>> getItemsDB(){
      return shoppingDao.getItems();
    }





}
