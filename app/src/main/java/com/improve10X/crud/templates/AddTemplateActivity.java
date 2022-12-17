package com.improve10X.crud.templates;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends  BaseAddTemplateActivity{
    private Button addTemplateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
        setupView();
        handleAddTemplateButton();
        handelAdd();
    }

    private void setupView() {
        addTemplateButton = findViewById(R.id.add_template_btn);
    }

    private void handelAdd() {
        addTemplateButton.setVisibility(View.VISIBLE);
    }

    private void handleAddTemplateButton() {
        addTemplateButton.setOnClickListener(view -> {
            String messageText = messageTextTxt.getText().toString();
            Template template = createTemplate(messageText);
            saveTemplate(template);
        });
    }

    private void saveTemplate(Template template) {
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("SuccessFully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }
}
