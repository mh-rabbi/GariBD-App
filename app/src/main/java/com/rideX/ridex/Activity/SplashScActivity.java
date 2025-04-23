package com.rideX.ridex.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.rideX.ridex.R;

public class SplashScActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_sc);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    runOnUiThread(() -> {
                        startActivity(new Intent(SplashScActivity.this, CarViewActivity.class));
                        finish();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}