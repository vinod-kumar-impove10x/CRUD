package com.improve10X.crud.series;

import com.improve10X.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesItemsApi {

    public SeriesItemsService createSeriesItemService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SeriesItemsService seriesItemsService = retrofit.create(SeriesItemsService.class);
        return seriesItemsService;
    }
}
