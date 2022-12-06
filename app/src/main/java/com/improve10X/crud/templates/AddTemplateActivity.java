package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.R;
import com.improve10X.crud.messages.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        getSupportActionBar().setTitle("Add Template");
        handleAddTemplateButton();

    }

    private void handleAddTemplateButton() {
            Button addTemplateButton = findViewById(R.id.add_template_btn);
            addTemplateButton.setOnClickListener(view -> {
                EditText messageTextTxt = findViewById(R.id.message_text_txt);
                String messageText = messageTextTxt.getText().toString();
                createTemplate(messageText);
        });
    }

    private void createTemplate(String messageText) {
        Template template = new Template();
        template.messageText = messageText;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Template> call = templatesService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddTemplateActivity.this, "SuccessFully loaded", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddTemplateActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();

            }
        });
    }
}