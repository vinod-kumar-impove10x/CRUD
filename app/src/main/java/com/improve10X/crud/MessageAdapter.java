package com.improve10X.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    public ArrayList<Message> messages;

    public void setData(ArrayList<Message> messageArrayList) {
        messages = messageArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item,parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.nameText.setText(message.name);
        holder.numberText.setText(message.number);
        holder.messageText.setText(message.message);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
