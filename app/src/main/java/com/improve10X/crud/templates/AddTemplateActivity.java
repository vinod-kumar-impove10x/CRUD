package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {

    private CrudService crudService;
    private EditText messageTextTxt;
    private Button addTemplateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        getSupportActionBar().setTitle("Add Template");
        setupView();
        setupApiService();
        handleAddTemplateButton();
    }

    private void setupView() {
        addTemplateButton = findViewById(R.id.add_template_btn);
        messageTextTxt = findViewById(R.id.message_text_txt);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
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

    private Template createTemplate(String messageText) {
        Template template = new Template();
        template.messageText = messageText;
        return template;
    }
}