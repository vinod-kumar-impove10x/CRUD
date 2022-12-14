package com.improve10X.crud.quotes;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("_id")
    String id;
    String quoteText;
    String category;
    String authorName;
    String imageUrl;
}
