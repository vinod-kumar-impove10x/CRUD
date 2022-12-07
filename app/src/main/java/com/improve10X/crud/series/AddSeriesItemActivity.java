package com.improve10X.crud.series;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series_item);
        getSupportActionBar().setTitle("Add Series");
        handleAdd();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText seriesIdTxt = findViewById(R.id.series_id_txt);
            String id = seriesIdTxt.getText().toString();
            EditText seriesNameTxt = findViewById(R.id.series_name_txt);
            String name = seriesNameTxt.getText().toString();
            EditText seriesImgUrl = findViewById(R.id.series_imgurl_txt);
            String imgUrl = seriesImgUrl.getText().toString();
            createSeriesItem(id,name,imgUrl);
        });
    }

    private void createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;

        SeriesItemsApi seriesItemsApi = new SeriesItemsApi();
        SeriesItemsService seriesItemsService = seriesItemsApi.createSeriesItemService();
        Call<SeriesItem> call = seriesItemsService.createSeriesItem(series);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                Toast.makeText(AddSeriesItemActivity.this, "Successfully loaded", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                Toast.makeText(AddSeriesItemActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }
}