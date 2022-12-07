package com.improve10X.crud.series;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView seriesImg;
    public TextView titleTxt;
    public ImageView deleteBtn;

    public SeriesItemViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImg = itemView.findViewById(R.id.series_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
