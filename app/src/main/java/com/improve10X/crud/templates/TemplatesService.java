package com.improve10X.crud.templates;

import com.improve10X.crud.Constants;
import com.improve10X.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplatesService {
    @GET(Constants.TEMPLATE_END_POINT)
    Call<List<Template>> fetchTemplates();

    @POST(Constants.TEMPLATE_END_POINT)
    Call<Template> createTemplate(@Body Template template);

    @DELETE(Constants.TEMPLATE_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);
}
