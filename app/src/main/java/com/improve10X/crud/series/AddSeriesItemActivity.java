package com.improve10X.crud.series;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {

    private CrudService crudService;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesImgUrl;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series_item);
        getSupportActionBar().setTitle("Add Series");
        setupView();
        setupApiService();
        handleAdd();
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
    }

    private void handleAdd() {
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