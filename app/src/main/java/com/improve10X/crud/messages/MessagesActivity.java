package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

    private CrudService crudService;
    private ArrayList<Message> messages;
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        setupApiService();
        log("onCreate");
        handleAdd();
        setupData();
        setupMessagesRv();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteMessage(Message message) {
        Call<Void> call = crudService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMessages();
    }

    private void fetchMessages() {
       Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                showToast("Wrong");
            }
        });
    }

    private void setupMessagesRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesRv.setAdapter(messagesAdapter);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
               showToast("onItemClicked");
            }

            @Override
            public void onItemDelete(Message message) {
                showToast("onItemDeleted");
                deleteMessage(message);

            }

            @Override
            public void onItemEdit(Message message) {
               showToast("onItemClicked");

            }
        });
    }

    private void setupData() {
            messages = new ArrayList<>();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
        });
    }
}