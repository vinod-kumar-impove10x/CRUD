package com.improve10X.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        getSupportActionBar().setTitle("Add Message");
    }
}