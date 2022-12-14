package com.improve10X.crud.quotes;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("_id")
    String id;
    String quotesText;
    String category;
    String authorName;
    String imageUrl;
}
