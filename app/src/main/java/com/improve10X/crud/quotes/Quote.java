package com.improve10X.crud.quotes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quote implements Serializable {
    @SerializedName("_id")
    String id;
    String quoteText;
    String category;
    String authorName;
    String imageUrl;
}
