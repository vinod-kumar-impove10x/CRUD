package com.improve10X.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10X.crud.Constants;
import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddMessageActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText nameTxt;
    protected EditText phoneNumberTxt;
    protected EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setupViews();
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected Message createMessage(String nameText, String phoneText, String messageText) {
        Message message = new Message();
        message.name = nameText;
        message.number = phoneText;
        message.messageText = messageText;
        return message;
    }

     private void setupViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_txt);
        messageTxt = findViewById(R.id.message_txt);
    }
}