package com.improve10X.crud.templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.Constants;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddTemplateActivity{
    private Template template;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_TEMPLATE)) {
            //Edit mode
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
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

    private void showData() {
        messageTextTxt .setText(template.messageText);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String messageText = messageTextTxt.getText().toString();
            Template updatedTemplate = createTemplate(messageText);
            updateTemplate(template.id,updatedTemplate);

        });
    }

    private void updateTemplate(String id, Template updatedTemplate) {
        Call<Void> call = crudService.updateTemplate(id, updatedTemplate);
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
