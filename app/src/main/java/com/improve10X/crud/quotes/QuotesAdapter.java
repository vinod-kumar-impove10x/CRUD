package com.improve10X.crud.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10X.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quotes;
    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener listener) {

        onItemActionListener = listener;
    }

    void setData(List<Quote> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item, parent,false);
        QuoteViewHolder viewHolder = new QuoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
         Quote quote = quotes.get(position);
         Picasso.get().load(quote.imageUrl).into(holder.quoteImg);
         holder.quoteTextTxt.setText(quote.quoteText);
         holder.authorNameTxt.setText(quote.authorName);
         holder.deleteBtn.setOnClickListener(v -> {
             onItemActionListener.onItemDelete(quote);
         });
         holder.itemView.setOnClickListener(view -> {
             onItemActionListener.onItemClick(quote);
         });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
