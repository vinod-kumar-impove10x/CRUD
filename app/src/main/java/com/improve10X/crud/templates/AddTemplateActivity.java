package com.improve10X.crud.templates;

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
import com.improve10X.crud.messages.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {

    private CrudService crudService;
    private EditText messageTextTxt;
    private Button addTemplateButton;
    private Template template;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        setupView();
        setupApiService();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_TEMPLATE)) {
            //Edit mode
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            handleEdit();
            handleEditBtn();
        }else {
            //Add mode
            getSupportActionBar().setTitle("Add Template");
            handleAddTemplateButton();
            handelAdd();
        }
    }

    private void handelAdd() {
        addTemplateButton.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void handleEditBtn() {
        addTemplateButton.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
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


    private void showData() {
         messageTextTxt .setText(template.messageText);
    }

    private void setupView() {
        addTemplateButton = findViewById(R.id.add_template_btn);
        messageTextTxt = findViewById(R.id.message_text_txt);
        editBtn = findViewById(R.id.edit_btn);
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