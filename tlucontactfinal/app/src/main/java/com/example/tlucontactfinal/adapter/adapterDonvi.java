package com.example.tlucontactfinal.adapter;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.model.donvi;

import java.io.File;
import java.util.ArrayList;

public class adapterDonvi extends RecyclerView.Adapter<adapterDonvi.ViewHolder>{
    ArrayList <donvi> listdonvi;

    public OnItemClickListener listener;
    public interface OnItemClickListener {
        void onAvatarClick(int position);
        void onCallClick(int position);
        }
    public void setOnItemClick(OnItemClickListener listener) {
        this.listener = listener;
    }

    public adapterDonvi(ArrayList<donvi> listdonvi) {
        this.listdonvi = listdonvi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        donvi donvi = listdonvi.get(position);
        holder.bind(donvi);
        holder.imgavtar.setOnClickListener(view -> {
            if (listener != null) {
                listener.onAvatarClick(position);
            }
        });
        holder.imgcall.setOnClickListener(view -> {
            if (listener != null) {
                listener.onCallClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listdonvi.size();
    }
    public void updateList(ArrayList<donvi> newList) {
        listdonvi = newList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgavtar, imgcall;
        TextView txtname, txtphone, txtemail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgavtar = itemView.findViewById(R.id.img_avatar);
            imgcall = itemView.findViewById(R.id.imgcall);
            txtname = itemView.findViewById(R.id.txt_name);
            txtphone = itemView.findViewById(R.id.txt_phone);
            txtemail = itemView.findViewById(R.id.txtemail);



        }

        public void bind(donvi donvi) {
            String avatarUri = donvi.getAvatar();
            Uri imageUri = Uri.parse(avatarUri);

            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(imgavtar.getContext())
                        .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                        .circleCrop()
                        .placeholder(R.drawable.inbox) // Ảnh mặc định nếu đang load
                        .error(R.drawable.inbox) // Ảnh mặc định nếu load thất bại
                        .into(imgavtar);
            } else {
                imgavtar.setImageResource(R.drawable.inbox);
            }

            txtname.setText(donvi.getTendv());
            txtphone.setText(donvi.getSdt());
            txtemail.setText(donvi.getEmail());

        }



    }
}
