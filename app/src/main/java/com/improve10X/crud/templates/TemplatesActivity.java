package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.R;
import com.improve10X.crud.messages.Message;
import com.improve10X.crud.messages.MessagesApi;
import com.improve10X.crud.messages.MessagesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void deleteTemplate(Template template) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Void> call = templatesService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplates();
    }

    private void fetchTemplates() {
        TemplatesApi templatesApi = new TemplatesApi();
        Call<List<Template>> call = templatesApi.createTemplatesService().fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        });
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
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Toast.makeText(TemplatesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Template template) {
                Toast.makeText(TemplatesActivity.this, "onItemDeleted", Toast.LENGTH_SHORT).show();
                deleteTemplate(template);

            }

            @Override
            public void onItemEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void handleAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }
}