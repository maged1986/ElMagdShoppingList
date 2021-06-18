package com.magednan.elmagdshoppinglist.ui.frag;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.magednan.elmagdshoppinglist.R;
import com.magednan.elmagdshoppinglist.databinding.SignInFragmentBinding;
import com.magednan.elmagdshoppinglist.databinding.SignUpFragmentBinding;
import com.magednan.elmagdshoppinglist.ui.activities.MainActivity;
import com.magednan.elmagdshoppinglist.viewmodes.SignUpViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private SignUpFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.sign_up_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doStringsMatch(binding.registerEtPassword, binding.registerEtConfirmPassword)) {
                    if ((checkField(binding.registerEtEmail) == true
                            && checkField(binding.registerEtName) == true
                            && checkField(binding.registerEtPassword) == true
                            && checkField(binding.registerEtConfirmPassword) == true)
                    ) {
                        mViewModel.singup(binding.registerEtName.getText().toString()
                                , binding.registerEtEmail.getText().toString()
                                , binding.registerEtPassword.getText().toString(),
                                getContext());
                    } else {
                        Toast.makeText(getContext(), "this is required field", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getContext(), "paswords doesnmatch", Toast.LENGTH_SHORT).show();
                }
                resetFields();
            }
        });

       binding.registerTvLinkLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_signInFragment);
           }
       });
    }
    private boolean doStringsMatch(EditText editText1, EditText editText2) {
        return editText1.getText().toString().equals(editText2.getText().toString());
    }

    private void resetFields() {
        binding.registerEtEmail.setText(null);
        binding.registerEtName.setText(null);
        binding.registerEtPassword.setText(null);
        binding.registerEtConfirmPassword.setText(null);
    }

    private Boolean checkField(EditText editText) {
        if (editText.getText().toString().length() > 2) {
            return true;
        } else {
            editText.setError("this is reqired field");
            Toast.makeText(getContext(),"please fill all required fields",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}