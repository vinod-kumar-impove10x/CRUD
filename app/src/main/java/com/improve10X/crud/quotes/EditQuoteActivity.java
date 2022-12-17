package com.improve10X.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10X.crud.Constants;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuoteActivity extends BaseAddEditQuoteActivity {

    private Quote quote;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_QUOTE)) {
            //Edit
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData(quote);
            showEditBtn();
            handleEdit();
        }
    }

    private void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData(Quote quote) {
        quoteTextTxt.setText(quote.quoteText);
        authorNameTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        imageUrlTxt.setText(quote.imageUrl);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(v -> {
            //Create a quote object with the data
            String quoteText = quoteTextTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote updatedQuote = createQuote(quoteText, authorName, category, imageUrl);
            // Make update api call
            updateQuote(quote.id, updatedQuote);

        });
    }

    private void updateQuote(String id, Quote updatedQuote) {
        Call<Void> call = crudService.updateQuote(id, updatedQuote);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Update quote successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update quote");
            }
        });
    }
}