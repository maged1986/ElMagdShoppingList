package com.magednan.elmagdshoppinglist.viewmodes;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.magednan.elmagdshoppinglist.repository.AuthRepository;

public class SignUpViewModel extends ViewModel {
    private AuthRepository repository;
@ViewModelInject
    public SignUpViewModel(AuthRepository repository) {
        this.repository = repository;
    }
    public void singup(String name, String email, String password, Context context){
        repository.singup(name, email, password, context);}
}