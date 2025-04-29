package com.rideX.ridex.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rideX.ridex.Adapter.RentAdapter;
import com.rideX.ridex.R;

public class RentPage extends AppCompatActivity {
    RecyclerView rentView;
    LinearLayout layoutBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rent_page);

        RentAdapter rentAdapter = new RentAdapter();
        layoutBack = findViewById(R.id.back_setting);
        rentView = findViewById(R.id.rent_view);
        rentView.setLayoutManager(new LinearLayoutManager(this));
        rentView.setAdapter(rentAdapter);


        layoutBack.setOnClickListener(v -> finish());
    }
}