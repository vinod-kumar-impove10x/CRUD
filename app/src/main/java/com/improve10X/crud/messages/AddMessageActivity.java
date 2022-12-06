package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        getSupportActionBar().setTitle("Add Message");
        handleBtn();
    }

    private void handleBtn() {
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

        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Message> call = messagesService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(AddMessageActivity.this, "SuccessFully loaded", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(AddMessageActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();

            }
        });
    }
}