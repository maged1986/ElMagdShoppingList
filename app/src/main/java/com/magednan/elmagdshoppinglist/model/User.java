package com.magednan.elmagdshoppinglist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class User {
    @PrimaryKey(autoGenerate = true)
    private String user_id;
    private String name;
    private String email;

    public User(String user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
