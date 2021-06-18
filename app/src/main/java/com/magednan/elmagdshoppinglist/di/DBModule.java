package com.magednan.elmagdshoppinglist.di;

import android.app.Application;

import androidx.room.Room;

import com.magednan.elmagdshoppinglist.db.ShoppingDao;
import com.magednan.elmagdshoppinglist.db.ShoppingItemsDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

    @Module
    @InstallIn(ApplicationComponent.class)
    public class DBModule {
        @Provides
        @Singleton
        public static ShoppingItemsDB provideDB(Application application){
            return Room.databaseBuilder(application, ShoppingItemsDB.class, "items_DB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        @Provides
        @Singleton
        public static ShoppingDao provideDao(ShoppingItemsDB shoppingItemsDB){
            return shoppingItemsDB.shoppingDao();
        }
    }
