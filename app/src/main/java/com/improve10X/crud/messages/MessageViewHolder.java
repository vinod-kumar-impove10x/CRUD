package com.improve10X.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public TextView nameText;
    public TextView numberText;
    public TextView messageTextTxt;
    public ImageButton deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.name_txt);
        numberText = itemView.findViewById(R.id.number_txt);
        messageTextTxt = itemView.findViewById(R.id.message_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
