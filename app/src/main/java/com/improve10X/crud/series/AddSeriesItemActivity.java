package com.improve10X.crud.series;

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
import com.improve10X.crud.messages.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {

    private CrudService crudService;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesImgUrl;
    private Button addBtn;
    private Button editBtn;
    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series_item);
        setupView();
        setupApiService();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        }else {
            getSupportActionBar().setTitle("Add Message");
            handleAddSeriesItem();
            showAddBtn();
        }
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String id = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = seriesImgUrl.getText().toString();
            SeriesItem updateSeriesItem = createSeriesItem(id,title,imageUrl);
            updateSeriesItem(seriesItem.id,updateSeriesItem);
        });
    }

    private void updateSeriesItem(String id, SeriesItem updateSeriesItem) {
        Call<Void> call = crudService.updateSeriesItem(id,updateSeriesItem);
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
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesImgUrl.setText(seriesItem.imageUrl);

    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupView() {
        addBtn = findViewById(R.id.add_btn);
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImgUrl = findViewById(R.id.series_imgurl_txt);
        editBtn = findViewById(R.id.edit_btn);
    }

    private void handleAddSeriesItem() {
        addBtn.setOnClickListener(view -> {
            String id = seriesIdTxt.getText().toString();
            String name = seriesNameTxt.getText().toString();
            String imgUrl = seriesImgUrl.getText().toString();
            SeriesItem seriesItem = createSeriesItem(id, name, imgUrl);
            saveSeriesItem(seriesItem);
        });
    }

    private void saveSeriesItem(SeriesItem seriesItem) {
        Call<SeriesItem> call = crudService.createSeriesItem(seriesItem);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });

    }

    private SeriesItem createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;
        return series;
    }
}