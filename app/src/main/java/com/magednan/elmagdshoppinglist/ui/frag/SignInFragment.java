package com.magednan.elmagdshoppinglist.ui.frag;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
import com.magednan.elmagdshoppinglist.viewmodes.SignInViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private SignInFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

      binding= DataBindingUtil.inflate(inflater,R.layout.sign_in_fragment, container, false);
      return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if ((checkField(binding.loginEtEmail) == true
                            && checkField(binding.loginEtPassword) == true)
                    ) {
                        mViewModel.login(binding.loginEtEmail.getText().toString()
                                , binding.loginEtPassword.getText().toString(),
                                getContext());

                    } else {
                        Toast.makeText(getContext(), "this is required field", Toast.LENGTH_SHORT).show();
                    }
                resetFields();
            }
        });

        binding.loginTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_signUpFragment);
            }
        });
    }

    private void resetFields() {
        binding.loginEtEmail.setText(null);
        binding.loginEtPassword.setText(null);
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