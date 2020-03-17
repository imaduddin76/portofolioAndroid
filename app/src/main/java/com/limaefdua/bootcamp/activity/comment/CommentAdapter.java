package com.limaefdua.bootcamp.activity.comment;
//
// Created by maftuhin on 10/24/2019.
//

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.utils.Preferences;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<CommentModel> data;
    private Preferences preference;

    public CommentAdapter(Context context, List<CommentModel> data) {
        this.context = context;
        this.data = data;
        preference = new Preferences(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getUsername().equals(preference.getUsername())) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_comment_end, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_comment_start, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvUsername.setText(data.get(position).getUsername());
        holder.tvMessage.setText(data.get(position).getComment());
        holder.tvDate.setText(data.get(position).getDate());

        Glide.with(context).load("https://a.wattpad.com/cover/157520716-352-k426060.jpg")
                .circleCrop()
                .into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        TextView tvUsername;
        TextView tvMessage;
        TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }
}
