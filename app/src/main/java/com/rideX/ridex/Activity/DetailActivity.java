package com.rideX.ridex.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.rideX.ridex.R;

public class DetailActivity extends AppCompatActivity {

    ImageView carImage;
    TextView titleTxt, ratingTxt, descriptionTxt, totalCapacityTxt, milageRanTxt, contactBuyerTxt, priceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        carImage = findViewById(R.id.carImage);
        titleTxt = findViewById(R.id.titleTxt);
        ratingTxt = findViewById(R.id.ratingTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        totalCapacityTxt = findViewById(R.id.totalCapacityTxt);
        milageRanTxt = findViewById(R.id.milageRanTxt);
        contactBuyerTxt = findViewById(R.id.contactBuyerTxt);
        priceTxt = findViewById(R.id.priceTxt);

//        Intent intent = getIntent();
//        String title = intent.getStringExtra("title");
//        String picName = intent.getStringExtra("picName");
//        String contactBuyer = intent.getStringExtra("contactBuyer");
//        String milageRan = intent.getStringExtra("milageRan");
//        int price = intent.getIntExtra("price", 0);
//        double rating = intent.getDoubleExtra("rating", 0);
//        String totalCapacity = intent.getStringExtra("totalCapacity");
//        String description = intent.getStringExtra("description");
//
//        titleTxt.setText(title);
//        ratingTxt.setText(String.valueOf(rating));
//        descriptionTxt.setText(description);
//        totalCapacityTxt.setText(totalCapacity);
//        contactBuyerTxt.setText(contactBuyer);
//        milageRanTxt.setText(milageRan);
//        priceTxt.setText(price + "/=");

//        int imageId = getResources().getIdentifier(picName.replace(".png", ""), "drawable", getPackageName());
//        carImage.setImageResource(imageId);
    }
}
