package com.improve10X.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.improve10X.crud.messages.MessagesActivity;
import com.improve10X.crud.templates.TemplatesActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        handleMessagesImageButton();
        handleTemplatesImageButton();

    }

    private void handleMessagesImageButton() {
        ImageButton messagesButton = findViewById(R.id.messages_img);
        messagesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
        });
    }

    private void handleTemplatesImageButton() {
        ImageButton templatesButton = findViewById(R.id.templates_img);
        templatesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, TemplatesActivity.class);
            startActivity(intent);
        });
    }
}