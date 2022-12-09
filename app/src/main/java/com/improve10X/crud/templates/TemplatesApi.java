package com.improve10X.crud.templates;

import com.improve10X.crud.Constants;
import com.improve10X.crud.messages.MessagesService;

import javax.xml.transform.Templates;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TemplatesApi {
    public TemplatesService createTemplatesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TemplatesService templatesService = retrofit.create(TemplatesService.class);
        return templatesService;
    }
}
