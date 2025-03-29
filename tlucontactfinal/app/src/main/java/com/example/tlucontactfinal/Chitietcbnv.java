package com.example.tlucontactfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.model.cbnv;

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

        Glide.with(this)
                .load(Uri.parse(cbnv.getAvatar())) // Load đường dẫn từ database
                .circleCrop() // Bo tròn ảnh
                .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                .into(imgavatar); // ImageView hiển thị ảnh

        txtname.setText(cbnv.getTencb());
        txtsdt.setText(cbnv.getSdt());

        txtemaildetail.setText("email: " + cbnv.getEmail());
        txtchucvudetail.setText("Chức vụ: " + cbnv.getChucvu());
        txtthongtindetail.setText("Thong tin them: " + cbnv.getThongtin());

        btnsua.setOnClickListener(view -> {

            Intent intent1 = new Intent(Chitietcbnv.this, Suacbnv.class);
            intent1.putExtra("cbnv", cbnv);
            startActivity(intent1);

        });

        btnxoa.setOnClickListener(view -> {
            if (helper.deleteCbnv(cbnv.getId())){
                Intent intent1 = new Intent(Chitietcbnv.this, Danhbacbnv.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
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