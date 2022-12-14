package com.improve10X.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesItem implements Serializable {
    @SerializedName("_id")
    public String id;
    public String seriesId;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("title")
    public String title;
}
