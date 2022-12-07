package com.improve10X.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {
    OnItemActionListener onItemActionListener;

    public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

    public List<SeriesItem> seriesList;

    public void setData(List<SeriesItem>seriesItems) {
        seriesList = seriesItems;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SeriesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item,parent,false);
        SeriesItemViewHolder seriesItemViewHolder = new SeriesItemViewHolder(view);
        return seriesItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemViewHolder holder, int position) {
        SeriesItem series = seriesList.get(position);
        holder.titleTxt.setText(series.title);
        Picasso.get().load(series.imageUrl).into(holder.seriesImg);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(series);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
