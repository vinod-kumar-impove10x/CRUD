package com.improve10X.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.Constants;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddMessageActivity {
    private Message message;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGE)) {
            //Edit mode
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            handleEdit();
            handleEditBtn();
        }
    }

    private void setupView() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void handleEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData(){
        nameTxt.setText(message.name);
        phoneNumberTxt.setText(message.number);
        messageTxt.setText(message.messageText);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String messageText = messageTxt.getText().toString();
            Message updatedMessage = createMessage(name,phoneNumber,messageText);
            updateMessage(message.id,updatedMessage);
        });
    }

    private void updateMessage(String id, Message updatedMessage) {
        Call<Void> call = crudService.updateMessage(id, updatedMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Wrong");
            }
        });
    }
}
