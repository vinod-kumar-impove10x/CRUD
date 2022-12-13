package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.Constants;
import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText messageTxt;
    private  Message message;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_MESSAGE)) {
            //Edit mode
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            handleEdit();
            handleEditBtn();
        }else {
            //Add mode
            getSupportActionBar().setTitle("Add Quote");
            handleMessageAdd();
            handleAdd();
        }
    }

    private void handleAdd() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void handleEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
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

    private void showData(){

        nameTxt.setText(message.name);
        phoneNumberTxt.setText(message.number);
        messageTxt.setText(message.messageText);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
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

    private Message createMessage(String nameText, String phoneText, String messageText) {
        Message message = new Message();
        message.name = nameText;
        message.number = phoneText;
        message.messageText = messageText;
        return message;

    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_txt);
        messageTxt = findViewById(R.id.message_txt);
        editBtn = findViewById(R.id.edit_btn);
    }
}