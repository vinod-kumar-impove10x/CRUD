package com.improve10X.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {

    private ArrayList<Quote> quotes = new ArrayList<>();
    private QuotesAdapter quotesAdapter;
    private RecyclerView quotesRv;
    private Button addBtn;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupView();
        setupQuotesAdapter();
        setupRecyclerView();
        setupApiService();
        handleAdd();
    }

    private void handleAdd() {
        addBtn.setOnClickListener( view -> {
            Intent intent = new Intent(this,AddEditCodeActivity.class);
            startActivity(intent);
        } );
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuotes();
    }

    private void fetchQuotes() {
        Call<List<Quote>> call = crudService.fetchQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> quotes = response.body();
                quotesAdapter.setData(quotes);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
            showToast("failed to fetch quotes");
            }
        });
    }

    private void setupRecyclerView() {
        quotesRv.setLayoutManager(new LinearLayoutManager(this));
        quotesRv.setAdapter(quotesAdapter);
    }

    private void setupQuotesAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesAdapter.setData(quotes);
        quotesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClick(Quote quote) {
                showToast("Item clicked");
            }

            @Override
            public void onItemDelete(Quote quote) {
                 deleteQuote(quote);
            }

            private void deleteQuote(Quote quote) {
                Call<Void> call = crudService.deleteQuote(quote.id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        showToast("Successfully deleted the quote");
                        fetchQuotes();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                         showToast("Failed to delete the quote ");
                    }
                });
            }
        });
    }

    private void setupView() {
        quotesRv = findViewById(R.id.quotes_rv);
        addBtn = findViewById(R.id.add_btn);
    }
}