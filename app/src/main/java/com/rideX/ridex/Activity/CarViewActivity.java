package com.rideX.ridex.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rideX.ridex.Adapter.CarAdapter;
import com.rideX.ridex.Adapter.CategoryAdapter;
import com.rideX.ridex.Model.CarModel;
import com.rideX.ridex.Model.CategoryModel;
import com.rideX.ridex.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CarViewActivity extends AppCompatActivity {
    RecyclerView carRecyclerView, categoryRecyclerView;
    ArrayList<CarModel> carList = new ArrayList<>();
    ArrayList<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view);

        carRecyclerView = findViewById(R.id.carRecyclerView);
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);

        carRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchCarsAndCategories();
    }

    private void fetchCarsAndCategories() {
        String url = "YOUR_API_URL_HERE"; // Replace with API endpoint

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray categories = response.getJSONArray("Category");
                        JSONArray cars = response.getJSONArray("Cars");

                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject obj = categories.getJSONObject(i);
                            categoryList.add(new CategoryModel(
                                    obj.getInt("id"),
                                    obj.getString("picName"),
                                    obj.getString("title")
                            ));
                        }

                        for (int i = 0; i < cars.length(); i++) {
                            JSONObject obj = cars.getJSONObject(i);
                            carList.add(new CarModel(
                                    obj.getString("title"),
                                    obj.getString("picName"),
                                    obj.getString("EngineOutput"),
                                    obj.getString("HighestSpeed"),
                                    obj.getInt("price"),
                                    obj.getDouble("rating"),
                                    obj.getString("TotalCapacity"),
                                    obj.getString("description")
                            ));
                        }

                        CarAdapter carAdapter = new CarAdapter(CarViewActivity.this, carList);
                        carRecyclerView.setAdapter(carAdapter);

                        CategoryAdapter categoryAdapter = new CategoryAdapter(CarViewActivity.this, categoryList);
                        categoryRecyclerView.setAdapter(categoryAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(this).add(request);
    }
}
