package com.rideX.ridex.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.slider.RangeSlider;
import com.rideX.ridex.Adapter.CarAdapter;
import com.rideX.ridex.Adapter.CategoryAdapter;
import com.rideX.ridex.Model.CarModel;
import com.rideX.ridex.Model.CategoryModel;
import com.rideX.ridex.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarViewActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView categoryRecyclerView, carRecyclerView;
    private CategoryAdapter categoryAdapter;
    private CarAdapter carAdapter;
    private final List<CategoryModel> categoryList = new ArrayList<>();
    private final List<CarModel> carList = new ArrayList<>();
    private final List<CarModel> fullCarList = new ArrayList<>(); // backup for filtering

    private ProgressBar progressBar;

    private final String API_URL = "http://your-api-url.com/api/car-data"; // Change this to actual API endpoint

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view);

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        carRecyclerView = findViewById(R.id.carRecyclerView);
        progressBar = findViewById(R.id.progressBarCar); // progressBarCategory now didnt take for simplicity
        ImageView filterIcon = findViewById(R.id.img_filter);
        filterIcon.setOnClickListener(v -> showFilterDialog());

        ImageView homeBtn = findViewById(R.id.homeBtn);//bottom navigation panel home button
        ImageView favBtn = findViewById(R.id.fav_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarViewActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish(); // Optional: prevents user from going back to CarViewActivity when pressing back
            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarViewActivity.this, DetailActivity.class));
            }
        });

        // Set up RecyclerViews
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        carRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        categoryAdapter = new CategoryAdapter(this, categoryList, this);
        carAdapter = new CarAdapter(this, carList);

        categoryRecyclerView.setAdapter(categoryAdapter);
        carRecyclerView.setAdapter(carAdapter);

        fetchData();
    }

    private void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.filter_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        RangeSlider priceSlider = view.findViewById(R.id.sliderPrice);
        TextView priceRangeText = view.findViewById(R.id.priceRangeText);
        Button btnApply = view.findViewById(R.id.btnApplyFilter);
        // Show current price range on slider change
        priceSlider.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = priceSlider.getValues();
            priceRangeText.setText((float) values.get(0) + " - " + (float) values.get(1) + " Tk");
        });

        btnApply.setOnClickListener(v -> {
            int minPrice = Math.round(priceSlider.getValues().get(0));
            int maxPrice = Math.round(priceSlider.getValues().get(1));
            applyFilter(minPrice, maxPrice);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void applyFilter(int minPrice, int maxPrice) {
        List<CarModel> filteredList = new ArrayList<>();
        for (CarModel car : fullCarList) {
            try {
                if (car.getPrice() >= minPrice && car.getPrice() <= maxPrice) {
                    filteredList.add(car);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        carAdapter.setData(filteredList);
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, API_URL, null,
                response -> {
                    try {
                        if (response.getInt("status") == 200) {
                            JSONArray categories = response.getJSONArray("Category");
                            JSONArray cars = response.getJSONArray("Cars");

                            categoryList.clear();
                            carList.clear();
                            fullCarList.clear();

                            for (int i = 0; i < categories.length(); i++) {
                                JSONObject cat = categories.getJSONObject(i);
                                categoryList.add(new CategoryModel(
                                        cat.getInt("id"),
                                        cat.getString("picName"),
                                        cat.getString("title")
                                ));
                            }

                            for (int i = 0; i < cars.length(); i++) {
                                JSONObject car = cars.getJSONObject(i);
                                CarModel model = new CarModel(
                                        car.getString("title"),
                                        car.getString("picName"),
                                        car.getString("contactBuyer"),
                                        car.getString("milageRan"),
                                        car.getInt("price"),
                                        (float) car.getDouble("rating"),
                                        car.getString("TotalCapacity"),
                                        car.getString("description")
                                );
                                carList.add(model);
                                fullCarList.add(model);
                            }

                            categoryAdapter.notifyDataSetChanged();
                            carAdapter.notifyDataSetChanged();
                        }

                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Parse Error", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                },
                error -> {
                    Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyError", error.toString());
                    progressBar.setVisibility(View.GONE);
                });

        queue.add(jsonRequest);
    }

    // When a category is selected
    @Override
    public void onCategoryClick(int categoryId) {
        List<CarModel> filteredList = new ArrayList<>();

        // For simplicity, just filter by index (position of category)
        // later will be implement a backend-side filter if needed
        for (int i = 0; i < fullCarList.size(); i++) {
            if (i % categoryList.size() == categoryId) { // Fake match just for filtering purpose now
                filteredList.add(fullCarList.get(i));
            }
        }

        carAdapter.updateList(filteredList);
    }
}
