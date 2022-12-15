package com.improve10X.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditCodeActivity extends BaseActivity {
    private CrudService crudService;
    private EditText quoteTextTxt;
    private EditText authorNameTxt;
    private EditText categoryTxt;
    private EditText imageUrlTxt;
    private Button addBtn;
    private Button deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_code);
        getSupportActionBar().setTitle("Add Quote");
        setupViews();
        setupApiService();
        handleAdd();
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
    }
}