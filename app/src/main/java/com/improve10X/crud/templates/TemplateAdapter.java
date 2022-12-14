package com.improve10X.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

import java.util.ArrayList;
import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
   OnItemActionListener onItemActionListener;

   public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

   private List<Template> templates;

    void setData(List<Template> templatesList) {
        templates = templatesList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templates_item,parent,false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.messageTextTxt.setText(template.messageText);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(template);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(template);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
