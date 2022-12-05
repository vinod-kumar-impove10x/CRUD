package com.improve10X.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
    }
}