package com.improve10X.crud;

import com.improve10X.crud.Constants;
import com.improve10X.crud.CrudService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudApi {

    public CrudService createCrudService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrudService crudService = retrofit.create(CrudService.class);
        return crudService;
    }
}
