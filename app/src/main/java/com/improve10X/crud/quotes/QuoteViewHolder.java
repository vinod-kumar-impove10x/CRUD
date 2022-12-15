package com.improve10X.crud.quotes;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

     ImageView quoteImg;
     TextView quoteTextTxt;
     TextView authorNameTxt;
     ImageButton deleteBtn;


    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quoteImg = itemView.findViewById(R.id.quote_img);
        quoteTextTxt = itemView.findViewById(R.id.quote_text_txt);
        authorNameTxt = itemView.findViewById(R.id.author_name_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
