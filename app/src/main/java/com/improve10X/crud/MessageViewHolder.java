package com.improve10X.crud;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public TextView nameText;
    public TextView numberText;
    public TextView messageText;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.name_txt);
        numberText = itemView.findViewById(R.id.number_txt);
        messageText = itemView.findViewById(R.id.message_txt);
    }
}
