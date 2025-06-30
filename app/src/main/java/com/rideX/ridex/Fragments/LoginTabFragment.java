package com.rideX.ridex.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.rideX.ridex.Activity.HomePageActivity;
import com.rideX.ridex.R;

public class LoginTabFragment extends Fragment {
    private EditText edtEmail, edtPass;
    private Button loginButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);
      //  FirebaseAuth mAuth = FirebaseAuth.getInstance();
        edtEmail = view.findViewById(R.id.login_user);
        edtPass = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> handleLogin());

        return view;
    }
    private void handleLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        Intent i = new Intent(getActivity(), HomePageActivity.class);
        startActivity(i);

        if (!isValidEmail(email)) {
            edtEmail.setError("Invalid email format");
            return;
        }

        if (!isComplexPassword(password)) {
            edtPass.setError("Password must be 8+ chars, 1 upper, 1 number, 1 symbol");
        }


    }
    private boolean isComplexPassword(String password) {
        // At least 8 chars, 1 upper, 1 number, 1 special
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
