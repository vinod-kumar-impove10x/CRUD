package com.improve10X.crud.quotes;

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

public class BaseAddEditQuoteActivity extends BaseActivity {
    private CrudService crudService;
    private EditText quoteTextTxt;
    private EditText authorNameTxt;
    private EditText categoryTxt;
    private EditText imageUrlTxt;
    private Button addBtn;
    private Button editBtn;
    private Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_code);

        Intent intent = getIntent();
        setupViews();
        setupApiService();
        if(intent.hasExtra(Constants.KEY_QUOTE)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData(quote);
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Quote");
            showAddBtn();
            handleAdd();
        }

    }

    private void handleEdit() {
        editBtn.setOnClickListener(v ->  {
            //Create a quote object with the data
            String quoteText = quoteTextTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote updatedQuote = createQuote(quoteText, authorName, category, imageUrl);
            // Make update api call
            updateQuote(quote.id,updatedQuote);

        });
    }

    private void updateQuote(String id,Quote updatedQuote) {
       Call<Void> call = crudService.updateQuote(id,updatedQuote);
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

    private  void  showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private  void  showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData(Quote quote) {
        quoteTextTxt.setText(quote.quoteText);
        authorNameTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        imageUrlTxt.setText(quote.imageUrl);
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
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

    private Quote createQuote(String quoteText, String authorName, String category, String imageUrl) {
        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.authorName = authorName;
        quote.category = category;
        quote.imageUrl = imageUrl;
        return quote;
    }

    private void setupViews() {
        quoteTextTxt = findViewById(R.id.quote_text_txt);
        authorNameTxt = findViewById(R.id.author_name_txt);
        categoryTxt = findViewById(R.id.category_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
    }
}