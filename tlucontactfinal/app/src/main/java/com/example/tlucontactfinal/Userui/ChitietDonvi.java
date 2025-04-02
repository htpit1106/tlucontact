package com.example.tlucontactfinal.Userui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.DatabaseHelper;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.model.donvi;

public class ChitietDonvi extends AppCompatActivity {
    DatabaseHelper helper;
    ImageView imgavatar;
    TextView txtname, txtsdt, txtcall, txtinbox, txtemaildetail, txtthongtindetail;
    Button btnsua, btnxoa;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chitiet_donvi);
        anhxa();
        Actionbar();
        Intent intent = getIntent();
        donvi donvi = (donvi) intent.getSerializableExtra("donvi");
        String user = intent.getStringExtra("userrole");

        if (user.equals("admin")) {
            btnsua.setVisibility(View.VISIBLE);
            btnxoa.setVisibility(View.VISIBLE);
        }else{
            btnsua.setVisibility(View.GONE);
            btnxoa.setVisibility(View.GONE);
        }





        Glide.with(this)
                .load(donvi.getAvatar()) // Load đường dẫn từ database
                .circleCrop() // Bo tròn ảnh
                .placeholder(R.drawable.inbox) // Ảnh mặc định nếu chưa có ảnh
                .into(imgavatar); // ImageView hiển thị ảnh

        txtname.setText(donvi.getTendv());
        txtsdt.setText(donvi.getSdt());
        txtemaildetail.setText("email: " + donvi.getEmail());
        txtthongtindetail.setText("Thông tin thêm: " + donvi.getThongtin());
        btnsua.setOnClickListener(view -> {
            Intent intent1 = new Intent(ChitietDonvi.this, Suadonvi.class);
            intent1.putExtra("donvi", donvi);

            startActivity(intent1);
            finish();


        });
        btnxoa.setOnClickListener(view -> {
            if (helper.deleteDonvi(donvi.getId())) {
                finish();
            }
        });

        txtcall.setOnClickListener(view -> {
            String sdt = donvi.getSdt();
            Intent intent2 = new Intent(Intent.ACTION_DIAL);
            intent2.setData(Uri.parse("tel:" + sdt));
            startActivity(intent2);
        });

        txtinbox.setOnClickListener(view -> {
            String sdt = donvi.getSdt();
            // gui tin nhan
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
        toolbar = findViewById(R.id.tbdonvidetail);
        imgavatar = findViewById(R.id.imgavatardv);
        txtname = findViewById(R.id.txtnamedv);
        txtsdt = findViewById(R.id.txtsdtdv);
        txtcall = findViewById(R.id.txtcalldv);
        txtinbox = findViewById(R.id.txtinboxdv);
        txtemaildetail = findViewById(R.id.txtemaildetaildv);
        txtthongtindetail = findViewById(R.id.txtthongtindetaildv);
        btnsua = findViewById(R.id.btnsuadv);
        btnxoa = findViewById(R.id.btnxoadv);
        helper = new DatabaseHelper(this);

    }
}