package com.improve10X.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends AppCompatActivity {
    public ArrayList<SeriesItem> seriesList;
    public RecyclerView seriesItemsRv;
    public SeriesItemsAdapter seriesItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_item);
        getSupportActionBar().setTitle("Series");
        handleAdd();
        setupData();
        setupSeriesItemsRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeriesItems();
    }

    public void deleteSeriesItem(SeriesItem seriesItem) {
        SeriesItemsApi seriesItemsApi = new SeriesItemsApi();
        SeriesItemsService seriesItemsService = seriesItemsApi.createSeriesItemService();
        Call<Void> call = seriesItemsService.deleteSeriesItem(seriesItem.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesItemsActivity.this, "SuccessFully done", Toast.LENGTH_SHORT).show();
                fetchSeriesItems();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesItemsActivity.this, "Failed to get load", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addSeriesIntent = new Intent(this,AddSeriesItemActivity.class);
            startActivity(addSeriesIntent);
        });
    }
    private void fetchSeriesItems() {
         SeriesItemsApi seriesItemsApi = new SeriesItemsApi();
         SeriesItemsService seriesItemsService = seriesItemsApi.createSeriesItemService();
         Call<List<SeriesItem>> call = seriesItemsService.fetchSeriesItem();
         call.enqueue(new Callback<List<SeriesItem>>() {
             @Override
             public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                 List<SeriesItem> seriesItems = response.body();
                 seriesItemsAdapter.setData(seriesItems);
             }

             @Override
             public void onFailure(Call<List<SeriesItem>> call, Throwable t) {

             }
         });
    }

    private void setupSeriesItemsRv() {
        seriesItemsRv = findViewById(R.id.series_rv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsRv.setAdapter(seriesItemsAdapter);
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem seriesItem) {
                Toast.makeText(SeriesItemsActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(SeriesItem seriesItem) {
                Toast.makeText(SeriesItemsActivity.this, "onItemDelete", Toast.LENGTH_SHORT).show();
                deleteSeriesItem(seriesItem);
            }

            @Override
            public void onItemEdit(SeriesItem seriesItem) {
                Toast.makeText(SeriesItemsActivity.this, "onItemEdit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupData() {
        seriesList = new ArrayList<>();
    }


}