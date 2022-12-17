package com.improve10X.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("_id")
      String id;
    @SerializedName("description")
      String description;
    @SerializedName("name")
      String name;
    @SerializedName("imageUrl")
      String imageUrl;
    @SerializedName("seriesId")
      String series;
}
