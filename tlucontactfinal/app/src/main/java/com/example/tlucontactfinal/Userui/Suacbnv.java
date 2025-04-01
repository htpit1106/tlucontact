package com.example.tlucontactfinal.Userui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.DatabaseHelper;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.model.cbnv;

public class Suacbnv extends AppCompatActivity {
    ImageView imgavatar;
    EditText edtten, edtsdtupdate, edtemaiupdate, edtchucvupdate, edtthongtin;
    Button btnsua;
    DatabaseHelper helper;
    private Uri imageUri;
    Toolbar toolbar;
    String userrole = "adminn";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suacbnv);
        anhxa();
        Actionbar();

        Intent intent = getIntent();
        cbnv cbnv = (cbnv) intent.getSerializableExtra("cbnv");


        Glide.with(this)
                .load(Uri.parse(cbnv.getAvatar())) // Load đường dẫn từ database
                .circleCrop() // Bo tròn ảnh
                .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                .into(imgavatar); // ImageView hiển thị ảnh

        edtten.setText(cbnv.getTencb());
        edtsdtupdate.setText(cbnv.getSdt());
        edtemaiupdate.setText(cbnv.getEmail());
        edtchucvupdate.setText(cbnv.getChucvu());
        edtthongtin.setText(cbnv.getThongtin());

        imgavatar.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });
        btnsua.setOnClickListener(view -> {
            String ten = edtten.getText().toString();
            String sdt = edtsdtupdate.getText().toString();
            String email = edtemaiupdate.getText().toString();
            String chucvu = edtchucvupdate.getText().toString();
            String thongtin = edtthongtin.getText().toString();

            if(imageUri == null){
                imageUri = Uri.parse(cbnv.getAvatar());
            }
            String avatar = imageUri.toString();
            cbnv cbnv1 = new cbnv(cbnv.getId(), ten, sdt, email, chucvu,avatar, thongtin);
            helper.updateCbnv(cbnv.getId(), cbnv1);
            Intent intent1 = new Intent(Suacbnv.this, Danhbacbnv.class);
            intent1.putExtra("cbnv", cbnv1);
            intent1.putExtra("user", userrole);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();


        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
             imageUri = data.getData(); // Lấy URI của ảnh
            Glide.with(imgavatar.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.inbox) // Ảnh mặc định nếu đang load
                    .error(R.drawable.inbox) // Ảnh mặc định nếu load thất bại
                    .into(imgavatar);        }
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
    private void anhxa() {
        toolbar = findViewById(R.id.tbsuacbnv);
        imgavatar = findViewById(R.id.imgavatar1);
        edtten = findViewById(R.id.edtten1);
        edtsdtupdate = findViewById(R.id.edtsdtupdate1);
        edtemaiupdate = findViewById(R.id.edtemaiupdate1);
        edtchucvupdate = findViewById(R.id.edtchucvupdate1);
        edtthongtin = findViewById(R.id.edtthongtin1);
        btnsua = findViewById(R.id.btnsua1);
        helper = new DatabaseHelper(this);
    }
}