package com.example.tlucontactfinal.Userui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.DatabaseHelper;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.model.cbnv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Chitietcbnv extends AppCompatActivity {
    DatabaseHelper helper;
    ImageView imgavatar;
    TextView txtname, txtsdt, txtcall, txtinbox, txtemaildetail, txtchucvudetail, txtthongtindetail;
    Button btnsua, btnxoa;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chitietcbnv);
        anhxa();
        Actionbar();
        Intent intent = getIntent();
        cbnv cbnv = (cbnv) intent.getSerializableExtra("cbnv");
        String userrole = intent.getStringExtra("userrole");
        if (userrole.equals("admin")) {
            btnsua.setVisibility(View.VISIBLE);
            btnxoa.setVisibility(View.VISIBLE);
        }
        else {
            btnsua.setVisibility(View.GONE);
            btnxoa.setVisibility(View.GONE);
        }

        String avatarUri = cbnv.getAvatar();
        Uri imageUri = Uri.parse(avatarUri);

        if (imageUri != null) {
            Glide.with(this)
                    .load(imageUri) // Load đường dẫn từ database
                    .circleCrop() // Bo tròn ảnh
                    .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                    .into(imgavatar); // ImageView hiển thị ảnh

        }


        txtname.setText(cbnv.getTencb());
        txtsdt.setText(cbnv.getSdt());

        txtemaildetail.setText("email: " + cbnv.getEmail());
        txtchucvudetail.setText("Chức vụ: " + cbnv.getChucvu());
        txtthongtindetail.setText("Thong tin them: " + cbnv.getThongtin());

        btnsua.setOnClickListener(view -> {

            Intent intent1 = new Intent(Chitietcbnv.this, Suacbnv.class);
            intent1.putExtra("cbnv", cbnv);
            startActivity(intent1);
            finish();

        });

        btnxoa.setOnClickListener(view -> {
            if (helper.deleteCbnv(cbnv.getId())){

                finish();
                } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();

            }
        });
        txtcall.setOnClickListener(view -> {
            String sdt = cbnv.getSdt();
            Intent intent2 = new Intent(Intent.ACTION_DIAL);
            intent2.setData(Uri.parse("tel:" + sdt));
            startActivity(intent2);

        });

        txtinbox.setOnClickListener(view -> {
            String sdt = cbnv.getSdt();
            Intent intent2 = new Intent(Intent.ACTION_SENDTO);
            intent2.setData(Uri.parse("smsto:" + sdt));
            startActivity(intent2);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }



    private void anhxa() {
        toolbar = findViewById(R.id.tbcbnvdetail);
        imgavatar = findViewById(R.id.imgavatar);
        txtname = findViewById(R.id.txtname);
        txtsdt = findViewById(R.id.txtsdt);
        txtcall = findViewById(R.id.txtcall);
        txtinbox = findViewById(R.id.txtinbox);
        txtemaildetail = findViewById(R.id.txtemaildetail);
        txtchucvudetail = findViewById(R.id.txtchucvudetail);
        txtthongtindetail = findViewById(R.id.txtthongtindetail);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        helper = new DatabaseHelper(this);


    }
}