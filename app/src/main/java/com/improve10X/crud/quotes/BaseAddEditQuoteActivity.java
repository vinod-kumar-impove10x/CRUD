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
    protected CrudService crudService;
    protected EditText quoteTextTxt;
    protected EditText authorNameTxt;
    protected EditText categoryTxt;
    protected EditText imageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_code);
        setupViews();
        setupApiService();
    }

    protected Quote createQuote(String quoteText, String authorName, String category, String imageUrl) {
        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.authorName = authorName;
        quote.category = category;
        quote.imageUrl = imageUrl;
        return quote;
    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    private void setupViews() {
        quoteTextTxt = findViewById(R.id.quote_text_txt);
        authorNameTxt = findViewById(R.id.author_name_txt);
        categoryTxt = findViewById(R.id.category_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
    }
}