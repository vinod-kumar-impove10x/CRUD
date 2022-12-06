package com.improve10X.crud.templates;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

public class Template {
   @SerializedName("_id")
   public  String id;
   @SerializedName("description")
   public String messageText;
}
