package com.example.tlucontactfinal;

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
import com.example.tlucontactfinal.model.donvi;

public class Themdonvi extends AppCompatActivity {

    ImageView imgavatar;
    EditText edtten, edtsdtadd, edtemaiadd, edtthongtin;
    Button btnadd;
    DatabaseHelper helper;
    private Uri imageUri;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themdonvi);

        anhxa();
        Actionbar();



        imgavatar.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });
        btnadd.setOnClickListener(view -> {
            String ten = edtten.getText().toString();
            String sdt = edtsdtadd.getText().toString();
            String email = edtemaiadd.getText().toString();
            String thongtin = edtthongtin.getText().toString();
            String avatar="";
            if(imageUri == null)
                imageUri = Uri.parse("android.resource://com.example.tlucontactfinal/drawable/inbox");
            else {
                 avatar = imageUri.toString();
            }

            donvi dv = new donvi( ten, sdt, email, avatar, thongtin);
            helper.insertDonvi(dv);
            Intent intent1 = new Intent(Themdonvi.this, Danhbacbnv.class);
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


    private void anhxa() {
        toolbar = findViewById(R.id.tbadddonvi);
        imgavatar = findViewById(R.id.imgavatar3);
        edtten = findViewById(R.id.edtten3);
        edtsdtadd = findViewById(R.id.edtsdtadd3);
        edtemaiadd = findViewById(R.id.edtemaiadd3);
        edtthongtin = findViewById(R.id.edtthongtin3);
        btnadd = findViewById(R.id.btnadd3);
        helper = new DatabaseHelper(this);

    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}