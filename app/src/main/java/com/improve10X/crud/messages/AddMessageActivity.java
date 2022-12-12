package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends AppCompatActivity {

    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        getSupportActionBar().setTitle("Add Message");
        setupApiService();
        handleAdd();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText nameTextTxt = findViewById(R.id.name_txt);
            String nameText = nameTextTxt.getText().toString();
            EditText phoneTextTxt = findViewById(R.id.phone_txt);
            String phoneText = phoneTextTxt.getText().toString();
            EditText messageTextTxt = findViewById(R.id.message_txt);
            String messageText = messageTextTxt.getText().toString();
            createMessage(nameText,phoneText,messageText);
        });
    }

    private void createMessage(String nameText, String phoneText, String messageText) {
        Message message = new Message();
        message.name = nameText;
        message.number = phoneText;
        message.messageText = messageText;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Message> call = crudService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("SuccessFully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
               showToast("Failed to get loaded");

            }
        });
    }
}