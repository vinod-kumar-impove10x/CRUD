package com.improve10X.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10X.crud.Constants;
import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;
import com.improve10X.crud.messages.AddMessageActivity;
import com.improve10X.crud.messages.MessagesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {
    private CrudService crudService;
    private ArrayList<SeriesItem> seriesList;
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_item);
        getSupportActionBar().setTitle("Series");
        setupApiService();
        log("onCreate");
        handleAdd();
        setupData();
        setupAdapter();
        setupSeriesItemsRv();
    }

    private void setupAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem seriesItem) {
                Intent intent = new Intent(SeriesItemsActivity.this, AddSeriesItemActivity.class);
                intent.putExtra(Constants.KEY_SERIES, seriesItem);
                startActivity(intent);
            }

            @Override
            public void onItemDelete(SeriesItem seriesItem) {
                showToast("onItemDelete");
                deleteSeriesItem(seriesItem);
            }

            @Override
            public void onItemEdit(SeriesItem seriesItem) {
               showToast("onItemEdit");
            }
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onCreate");
        fetchSeriesItems();
    }

    public void deleteSeriesItem(SeriesItem seriesItem) {
        Call<Void> call = crudService.deleteSeriesItem(seriesItem.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("SuccessFully done");
                fetchSeriesItems();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get load");

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
         Call<List<SeriesItem>> call = crudService.fetchSeriesItem();
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
        seriesItemsRv.setAdapter(seriesItemsAdapter);

    }

    private void setupData() {
        seriesList = new ArrayList<>();
    }


}