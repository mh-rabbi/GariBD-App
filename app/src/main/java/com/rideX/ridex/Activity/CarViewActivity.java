package com.rideX.ridex.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rideX.ridex.Domain.CategoryDomain;
import com.rideX.ridex.R;
import com.rideX.ridex.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class CarViewActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //setContentView(R.layout.activity_car_view);
        setContentView(binding.getRoot());

        initCategoryList();
    }

    private void initCategoryList() {
        DatabaseReference myref = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<CategoryDomain> items = new ArrayList<>();
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(snapsot.exists()){
                    for(DataSnapshot issue : snapshot.getChildren()){
                        items.add(issue.getValue(CategoryDomain.class));
                    }
                    if(!items.isEmpty())  {
                        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(CarViewActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerViewCategory.setAdapter(new CategoryAdapter(items));
                        binding.recyclerViewCategory.setNestedScrollingEnabled(true);
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}