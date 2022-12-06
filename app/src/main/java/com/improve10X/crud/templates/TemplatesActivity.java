package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10X.crud.R;

import java.util.ArrayList;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Template> templates;
    public RecyclerView templatesRV;
    public TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        handleAddButton();
        setupData();
        setupTemplatesRv();

    }

    private void setupData() {
        templates = new ArrayList<>();
        Template template = new Template();
        template.messageText = " hi hello improve 10X";
        templates.add(template);
    }

    private void setupTemplatesRv() {
        templatesRV = findViewById(R.id.templates_rv);
        templatesRV.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplateAdapter();
        templateAdapter.setData(templates);
        templatesRV.setAdapter(templateAdapter);
    }

    private void handleAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }
}