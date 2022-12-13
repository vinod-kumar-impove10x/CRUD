package com.improve10X.crud.messages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {
   @SerializedName("_id")
   public String id;
   @SerializedName("name")
    public String name;
   @SerializedName("phoneNumber")
    public String number;
   @SerializedName("messageText")
    public String messageText;

}
