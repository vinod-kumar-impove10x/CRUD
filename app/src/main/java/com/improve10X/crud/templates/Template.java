package com.improve10X.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.http.GET;

public class Template implements Serializable {
   @SerializedName("_id")
   public  String id;
   @SerializedName("description")
   public String messageText;
}
