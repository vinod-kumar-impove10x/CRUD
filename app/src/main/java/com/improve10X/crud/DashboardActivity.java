package com.improve10X.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        handleImageButton();

    }

    private void handleImageButton() {
        ImageButton messagesButton = findViewById(R.id.messages_img);
        messagesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,MessagesActivity.class);
            startActivity(intent);
        });
    }
}