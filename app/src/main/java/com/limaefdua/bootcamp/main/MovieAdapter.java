package com.limaefdua.bootcamp.main;
//
// Created by maftuhin on 10/18/2019.
//

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.activity.DetailActivity;
import com.limaefdua.bootcamp.main.model.Film;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private List<Film> data;

    public MovieAdapter(Context context, List<Film> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvDescription.setText(data.get(position).getCategory_name());
        Glide.with(context).load(data.get(position).getImage())
                .error(R.drawable.ic_video)
                .transform(new CenterCrop(), new RoundedCorners(12))
                .into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("data", new Gson().toJson(data.get(holder.getAdapterPosition())));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imgMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgMovie = itemView.findViewById(R.id.imgMovie);
        }
    }
}
