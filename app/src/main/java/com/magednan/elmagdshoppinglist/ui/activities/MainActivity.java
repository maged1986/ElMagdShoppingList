package com.magednan.elmagdshoppinglist.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.magednan.elmagdshoppinglist.R;
import com.magednan.elmagdshoppinglist.adapter.ItemsAdapter;
import com.magednan.elmagdshoppinglist.databinding.ActivityMainBinding;
import com.magednan.elmagdshoppinglist.firebase.FireBaseAuth;
import com.magednan.elmagdshoppinglist.model.ShoppingItem;
import com.magednan.elmagdshoppinglist.viewmodes.MainViewModel;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ItemsAdapter adapter;

    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolBar);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
            }
        });
        adapter = new ItemsAdapter(this);
        binding.mainRv.setAdapter(adapter);
        viewModel.getItemsDB().observe(this, new Observer<List<ShoppingItem>>() {
            @Override
            public void onChanged(List<ShoppingItem> shoppingItems) {
                adapter.setList(shoppingItems);
                String price = viewModel.getPrice(shoppingItems) + "$";
                binding.totalAmount.setText(price);

                Log.d("TAG", "onChanged: " + viewModel.getPrice(shoppingItems));
            }
        });

    }

    private void customDialog() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.input_data_layout, null);
        final AlertDialog dialog = myDialog.create();
        dialog.setView(view);
        dialog.show();

        final EditText type = view.findViewById(R.id.edt_type);
        final EditText amount = view.findViewById(R.id.edt_amount);
        final EditText note = view.findViewById(R.id.edt_note);
        Button save = view.findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mType = type.getText().toString().trim();
                String mAmount = amount.getText().toString().trim();
                String mNote = note.getText().toString().trim();

                if (TextUtils.isEmpty(mType)) {
                    type.setError("Required field.");
                    return;
                }
                if (TextUtils.isEmpty(mAmount)) {
                    amount.setError("Required field.");
                    return;
                }
                if (TextUtils.isEmpty(mNote)) {
                    note.setError("Required field.");
                    return;
                }

                String date = DateFormat.getDateInstance().format(new Date());
                int amount = Integer.parseInt(mAmount);
                ShoppingItem data = new ShoppingItem(mType, amount, mNote, date);
                viewModel.uploadItem(data, MainActivity.this);
                viewModel.getItems();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.op_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.op_menu_signout){
            FirebaseAuth auth=FirebaseAuth.getInstance();
            auth.signOut();
            startActivity(new Intent(this,AuthActivity.class));
            return false;
        }
        else return super.onOptionsItemSelected(item);
    }

}