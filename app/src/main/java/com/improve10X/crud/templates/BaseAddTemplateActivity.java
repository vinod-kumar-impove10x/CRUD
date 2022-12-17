package com.improve10X.crud.templates;

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

public class BaseAddTemplateActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText messageTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        setupView();
        setupApiService();
    }

    private void setupView() {
        messageTextTxt = findViewById(R.id.message_text_txt);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected Template createTemplate(String messageText) {
        Template template = new Template();
        template.messageText = messageText;
        return template;
    }
}