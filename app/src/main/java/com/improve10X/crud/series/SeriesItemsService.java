package com.improve10X.crud.series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesItemsService {
    @GET("vinodSeries")
    Call<List<SeriesItem>> fetchSeriesItem();

    @POST("vinodSeries")
    Call<SeriesItem> createSeriesItem(@Body SeriesItem seriesItem);

    @DELETE("vinodSeries/{id}")
    Call<Void>deleteSeriesItem(@Path("id")String id);

}
