package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void deleteMessage(Message message) {
        MessagesApi api = new MessagesApi();
        MessagesService messagesService = api.createMessagesService();
        Call<Void> call = messagesService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessagesActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchData();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchData();
    }

    private void fetchData() {
        MessagesApi messagesApi = new MessagesApi();
        Call<List<Message>> call = messagesApi.createMessagesService().fetchTasks();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messageAdapter.setData(messages);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupMessages() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messages);
        messagesRv.setAdapter(messageAdapter);
        messageAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemDeleted", Toast.LENGTH_SHORT).show();
                deleteMessage(message);

            }

            @Override
            public void onItemEdit(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();

            }
        });


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