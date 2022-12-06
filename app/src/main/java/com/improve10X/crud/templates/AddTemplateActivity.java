package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10X.crud.R;

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

    }
}