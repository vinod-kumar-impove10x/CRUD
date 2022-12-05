package com.improve10X.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    OnItemActionListener onItemActionListener;
    public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

    public List<Message> messages;

    public void setData(List<Message> messageArrayList) {
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
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(message);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(message);
        });

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
