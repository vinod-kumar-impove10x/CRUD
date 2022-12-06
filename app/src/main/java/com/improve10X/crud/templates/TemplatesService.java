package com.improve10X.crud.templates;

import com.improve10X.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplatesService {
    @GET("vinodTemplates")
    Call<List<Template>> fetchTemplates();

    @POST("vinodTemplates")
    Call<Template> createTemplate(@Body Template template);

    @DELETE("vinodTemplates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);
}
