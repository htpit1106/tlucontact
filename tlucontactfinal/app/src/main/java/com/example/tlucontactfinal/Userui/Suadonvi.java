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
import com.example.tlucontactfinal.model.donvi;

public class Suadonvi extends AppCompatActivity {
    ImageView imgavatar;
    EditText edtten, edtsdtupdate, edtemaiupdate, edtthongtin;
    Button btnsua;
    DatabaseHelper helper;
    private Uri imageUri;
    Toolbar toolbar;

    String userrole = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suadonvi);


        anhxa();
        Actionbar();
        Intent intent = getIntent();
        donvi dv = (donvi) intent.getSerializableExtra("donvi");


        Glide.with(this)
                .load(Uri.parse(dv.getAvatar())) // Load đường dẫn từ database
                .circleCrop() // Bo tròn ảnh
                .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                .into(imgavatar); // ImageView hiển thị ảnh

        edtten.setText(dv.getTendv());
        edtsdtupdate.setText(dv.getSdt());
        edtemaiupdate.setText(dv.getEmail());

        edtthongtin.setText(dv.getThongtin());

        imgavatar.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 2);

        });
        btnsua.setOnClickListener(view -> {
            String ten = edtten.getText().toString();
            String sdt = edtsdtupdate.getText().toString();
            String email = edtemaiupdate.getText().toString();

            String thongtin = edtthongtin.getText().toString();

            if(imageUri == null){
                imageUri = Uri.parse(dv.getAvatar());
            }
            String avatar = imageUri.toString();
            donvi dv1 = new donvi(dv.getId(), ten, sdt, email,avatar, thongtin);

            helper.updateDonvi(dv.getId(), dv1);
            Intent intent1 = new Intent(Suadonvi.this, danhbadonvi.class);
            intent1.putExtra("user", userrole);
            startActivity(intent1);
            finish();


        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
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



    private void anhxa(){
        toolbar = findViewById(R.id.tbsuadonvi);
        imgavatar = findViewById(R.id.imgavatar2);
        edtten = findViewById(R.id.edtten2);
        edtsdtupdate = findViewById(R.id.edtsdtupdate2);
        edtemaiupdate = findViewById(R.id.edtemaiupdate2);
        edtthongtin = findViewById(R.id.edtthongtin2);
        btnsua = findViewById(R.id.btnsua2);
        helper = new DatabaseHelper(this);


    }
}