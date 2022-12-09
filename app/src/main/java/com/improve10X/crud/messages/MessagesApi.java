package com.improve10X.crud.messages;

import com.improve10X.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesApi {
   public MessagesService createMessagesService() {
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(Constants.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       MessagesService messagesService = retrofit.create(MessagesService.class);
       return messagesService;
   }
}
