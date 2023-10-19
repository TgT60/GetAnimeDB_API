package com.example.kostyaproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Anime> animeList ;
    private ItemClickListener clickListener;

    public RecyclerViewAdapter(List<Anime> animeList,ItemClickListener clickListener){
        this.animeList = animeList;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.titleTextView.setText(animeList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(animeList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView titleTextView ;
        public MyViewHolder(View view){
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(Anime anime);
    }
}

