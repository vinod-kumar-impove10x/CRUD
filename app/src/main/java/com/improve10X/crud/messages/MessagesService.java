package com.improve10X.crud.messages;

import com.improve10X.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessagesService {
   @GET("messageHistory")
    Call<List<Message>>fetchTasks();

   @POST("messageHistory")
   Call<Message>createTasks(@Body Message message);

   @DELETE("messageHistory/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

}
