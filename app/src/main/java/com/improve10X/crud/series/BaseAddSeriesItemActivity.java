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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddSeriesItemActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText seriesImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series_item);
        setupView();
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImgUrl = findViewById(R.id.series_imgurl_txt);
    }

    protected SeriesItem createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;
        return series;
    }
}