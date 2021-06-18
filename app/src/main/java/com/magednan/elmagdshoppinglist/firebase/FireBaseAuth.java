package com.magednan.elmagdshoppinglist.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.magednan.elmagdshoppinglist.model.User;
import com.magednan.elmagdshoppinglist.ui.activities.MainActivity;

import javax.inject.Inject;

public class FireBaseAuth extends AppCompatActivity {
    private User mUser;
    @Inject
    public FireBaseAuth() {
    }


    public void createNewUser(String name, String email, String password, Context context
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //send email verificaiton
                            sendVerificationEmail(context);
                            updateUserData(name, email);
                            //add user details to firebase database

                            Toast.makeText(context, "Successful ",
                                    Toast.LENGTH_SHORT).show();
                            MainActivity.show(context);
                               }
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, "Someone with that email ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUserData(String name, String email) {
        //add data to the "users" node
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mUser = new User(userid, name, email);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        //insert into users node
        reference.child("users").push()
                .setValue(mUser);
    }

    public void sendVerificationEmail(Context mContext) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(mContext, "couldn't send a verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public void logIn(String email, String password, Context context) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // SessionManager manager = new SessionManager(context);

                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(context, "Authentication successe.",
                                    Toast.LENGTH_SHORT).show();
                            MainActivity.show(context);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signOut(Context context) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

}
