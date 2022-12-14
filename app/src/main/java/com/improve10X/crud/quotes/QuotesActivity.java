package com.improve10X.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import java.util.ArrayList;

public class QuotesActivity extends BaseActivity {

    private ArrayList<Quote> quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupData();
    }

    private void setupData() {
        quotes = new ArrayList<>();

        Quote quote1 = new Quote();
        quote1.id = "6397f97daaf0eb03e8f948d3";
        quote1.quoteText = "Your FUTURE is created by what you do TODAY";
        quote1.category = "career";
        quote1.authorName = "";
        quote1.imageUrl = "https://www.kochiesbusinessbuilders.com.au/wp-content/uploads/2022/02/motivational-quote.jpg";
        quotes.add(quote1);

        Quote quote2 = new Quote();
        quote2.id = "6397f97daaf0eb03e8f948d3";
        quote2.quoteText = "Your FUTURE is created by what you do TODAY";
        quote2.category = "career";
        quote2.authorName = "";
        quote2.imageUrl = "https://www.kochiesbusinessbuilders.com.au/wp-content/uploads/2022/02/motivational-quote.jpg";
        quotes.add(quote2);
    }
}