package com.example.appdocsach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class BookhotAdapter extends RecyclerView.Adapter<BookhotAdapter.ViewHolder> {
    ArrayList<Sach> mangsachhot;
    Context context;
    public BookhotAdapter(ArrayList<Sach> mangsachhot, Context context) {
        this.mangsachhot = mangsachhot;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.dong_sachmoi,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Sach sach = mangsachhot.get(position);
        holder.txtnamebook.setText(mangsachhot.get(position).getBookName());
        Picasso.with(context).load(mangsachhot.get(position).getImgHinh())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error_image)
                .into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return mangsachhot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnamebook;
        ImageView imgHinh;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtnamebook = (TextView) itemView.findViewById(R.id.txtsach);
            imgHinh = (ImageView) itemView.findViewById(R.id.imgsach);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Chitietsach.class);
                    intent.putExtra("chitietsach_key", mangsachhot.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }
}
