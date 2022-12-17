package com.improve10X.crud.movies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView movieImgImg;
    TextView titleTxt;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        movieImgImg = itemView.findViewById(R.id.movie_image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
