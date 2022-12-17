package com.improve10X.crud.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends BaseAddEditQuoteActivity{

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Quote");
        setupViews();
        showAddBtn();
        handleAdd();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
    }

    private  void  showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String quoteText = quoteTextTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote quote = createQuote(quoteText,authorName,category,imageUrl);
            saveQuote(quote);
        });
    }

    private void saveQuote(Quote quote) {
        //Make the Api call
        Call<Quote> call = crudService.createQuote(quote);
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                showToast("Successfully created quote");
                finish();
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                showToast("Failed to save quote");
            }
        });
    }
}
