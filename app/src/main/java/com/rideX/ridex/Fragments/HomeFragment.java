package com.rideX.ridex.Fragments;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.rideX.ridex.Activity.CarViewActivity;
import com.rideX.ridex.Activity.SupportActivity;
import com.rideX.ridex.R;

public class HomeFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        CardView btnPost = view.findViewById(R.id.cardbtn_post);
        CardView btnRent = view.findViewById(R.id.cardbtn_rent);
        CardView btnSetting = view.findViewById(R.id.btn_setting);
        CardView btnSupport = view.findViewById(R.id.btn_support);
        CardView btnCar = view.findViewById(R.id.btn_car);
        // CardView btnFavorite = view.findViewById(R.id.cardbtn_favorite);
       // CardView btnHistory = view.findViewById(R.id.cardbtn_history);
       // CardView btnBike = view.findViewById(R.id.btn_bike);

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CarViewActivity.class));
            }
        });
        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SupportActivity.class));
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
