package com.magednan.elmagdshoppinglist.repository;


import android.content.Context;

import com.magednan.elmagdshoppinglist.db.ShoppingDao;
import com.magednan.elmagdshoppinglist.firebase.FireBaseAuth;

import javax.inject.Inject;

public class AuthRepository {

    private FireBaseAuth manager;
    private ShoppingDao shoppingDao;

    @Inject
    public AuthRepository(FireBaseAuth manager) {
        this.manager = manager;
    }

    public void login(String email, String password, Context context){manager.logIn(email, password, context);}
    public void singup(String name, String email, String password, Context context){
        manager.createNewUser(name, email, password, context);}
}
