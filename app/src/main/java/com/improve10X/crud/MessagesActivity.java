package com.improve10X.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messages;
    public RecyclerView messagesRv;
    public MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        handleAddButton();
        setupDta();
        setupMessages();


    }

    private void setupMessages() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messages);
        messagesRv.setAdapter(messageAdapter);
    }

    private void setupDta() {
            messages = new ArrayList<>();
    }

    private void handleAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
        });
    }
}