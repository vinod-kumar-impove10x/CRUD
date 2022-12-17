package com.improve10X.crud.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddMessageActivity {

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
        setupViews();
        showAddBtn();
        handleMessageAdd();
    }

        void setupViews() {
        addBtn = findViewById(R.id.add_btn);
    }

    private  void  showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleMessageAdd() {
        addBtn.setOnClickListener(view -> {
            // Get data from edit text components. name, phoneNumber, messageText.
            String nameText = nameTxt.getText().toString();
            String phoneText = phoneNumberTxt.getText().toString();
            String messageText = messageTxt.getText().toString();
            //create message object using name, phoneNumber, messageTxt.
            Message message = createMessage(nameText,phoneText,messageText);
            //save message in server
            saveMessage(message);
        });
    }

    private void saveMessage(Message message) {
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

