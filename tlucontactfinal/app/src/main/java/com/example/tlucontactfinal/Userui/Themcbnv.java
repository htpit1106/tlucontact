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

public class Themcbnv extends AppCompatActivity {

    ImageView imgavatar;
    EditText edtten, edtsdtadd, edtemaiadd, edtchucvuadd, edtthongtin;
    Button btnadd;
    DatabaseHelper helper;
    private Uri imageUri;
    Toolbar toolbar;

    String userrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themcbnv);
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
            String chucvu = edtchucvuadd.getText().toString();
            String thongtin = edtthongtin.getText().toString();
            String avatar= "";

            if(imageUri == null)
                imageUri = Uri.parse("android.resource://com.example.tlucontactfinal/drawable/inbox");
            else {
                avatar = imageUri.toString();
            }

            cbnv cbnv1 = new cbnv( ten, sdt, email, chucvu,avatar, thongtin);
            helper.insertCbnv(cbnv1);
            Intent intent1 = new Intent(Themcbnv.this, Danhbacbnv.class);
            intent1.putExtra("user", userrole);
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
        toolbar = findViewById(R.id.tbaddcbnv);
        imgavatar = findViewById(R.id.imgavatar4);
        edtten = findViewById(R.id.edtten4);
        edtsdtadd = findViewById(R.id.edtsdtadd4);
        edtemaiadd = findViewById(R.id.edtemailadd4);
        edtchucvuadd = findViewById(R.id.edtchucvuadd4);
        edtthongtin = findViewById(R.id.edtthongtin4);
        btnadd = findViewById(R.id.btnThem4);
        helper = new DatabaseHelper(this);

    }


    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}