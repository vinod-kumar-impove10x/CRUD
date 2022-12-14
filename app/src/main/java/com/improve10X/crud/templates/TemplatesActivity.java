package com.improve10X.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;
import com.improve10X.crud.messages.Message;
import com.improve10X.crud.messages.MessagesApi;
import com.improve10X.crud.messages.MessagesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {
    private CrudService crudService;
    private ArrayList<Template> templates;
    private RecyclerView templatesRV;
    private TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setupApiService();
        log("onCreate");
        handleAddButton();
        setupData();
        setupAdapter();
        setupTemplatesRv();
    }

    private void setupAdapter() {
        templateAdapter = new TemplateAdapter();
        templateAdapter.setData(templates);
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                showToast("Successfully on clicked");
            }

            @Override
            public void onItemDelete(Template template) {
                showToast("onItemDeleted");
                deleteTemplate(template);

            }

            @Override
            public void onItemEdit(Template template) {
                showToast("onItemClicked");

            }
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteTemplate(Template template) {
        Call<Void> call = crudService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Wrong");
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