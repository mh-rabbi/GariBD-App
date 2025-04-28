package com.rideX.ridex.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rideX.ridex.R;

public class SupportActivity extends AppCompatActivity {
    private EditText edtMessage;
    private Button btnSend;
    LinearLayout layoutBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support);

        edtMessage = findViewById(R.id.edit_message);
        btnSend = findViewById(R.id.btn_send);
        layoutBack = findViewById(R.id.back_support);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSupportMessage();
            }
        });
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendSupportMessage() {
        String message = edtMessage.getText().toString().trim();
        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter your message...", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"garibd2025@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request from GariBD App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email via..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email app installed on your device.", Toast.LENGTH_LONG).show();
        }
        edtMessage.setText("");

    }
}