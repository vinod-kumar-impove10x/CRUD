package com.improve10X.crud.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.Constants;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddSeriesItemActivity{

    private Button editBtn;
    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

        private void showEditBtn() {
            editBtn.setVisibility(View.VISIBLE);
        }

        private void setupView() {
            editBtn = findViewById(R.id.edit_btn);
        }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesImgUrl.setText(seriesItem.imageUrl);
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
}
