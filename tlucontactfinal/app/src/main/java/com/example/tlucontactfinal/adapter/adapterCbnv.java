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
import com.example.tlucontactfinal.model.cbnv;

import java.io.File;
import java.util.ArrayList;

public class adapterCbnv extends  RecyclerView.Adapter<adapterCbnv.ViewHolder>{
    ArrayList <cbnv> listcbnv;

    public OnItemClickListener listener;
    public interface OnItemClickListener {
        void onAvatarClick(int position);
        void onCallClick(int position);
    }
    public void setOnItemClick(OnItemClickListener listener) {
        this.listener = listener;
    }

    public adapterCbnv(ArrayList<cbnv> listcbnv) {
        this.listcbnv = listcbnv;
    }

    public void updateList(ArrayList<cbnv> newList) {
        listcbnv = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cbnv cb = listcbnv.get(position);
        holder.bind(cb);
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
        return listcbnv.size();
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

        public void bind(cbnv cb) {

            String avatarUri = cb.getAvatar();
            Uri imageUri = Uri.parse(avatarUri);

            if (imageUri != null) {
                Glide.with(imgavtar.getContext())
                        .load(imageUri) // Load đường dẫn từ database
                        .circleCrop() // Bo tròn ảnh
                        .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                        .into(imgavtar); // ImageView hiển thị ảnh

            }

            txtname.setText(cb.getTencb());
            txtphone.setText(cb.getSdt());
            txtemail.setText(cb.getEmail());

        }

    }
}
