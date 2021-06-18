package com.magednan.elmagdshoppinglist.viewmodes;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.magednan.elmagdshoppinglist.repository.AuthRepository;

public class SignInViewModel extends ViewModel {
    private AuthRepository repository;

    @ViewModelInject
    public SignInViewModel(AuthRepository repository) {
        this.repository = repository;
    }
    public void login(String email, String password, Context context){repository.login(email, password, context);}

}