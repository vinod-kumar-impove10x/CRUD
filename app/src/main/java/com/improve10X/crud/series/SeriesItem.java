package com.improve10X.crud.series;

import com.google.gson.annotations.SerializedName;

public class SeriesItem {
    @SerializedName("_id")
    public String id;
    public String seriesId;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("title")
    public String title;
}
