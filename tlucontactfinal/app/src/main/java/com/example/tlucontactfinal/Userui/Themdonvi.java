package com.example.tlucontactfinal.Userui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tlucontactfinal.DatabaseHelper;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.model.donvi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Themdonvi extends AppCompatActivity {

    ImageView imgavatar;
    EditText edtten, edtsdtadd, edtemaiadd, edtthongtin;
    Button btnadd;
    DatabaseHelper helper;
    private Uri imageUri;
    Toolbar toolbar;

    String userrole = "admin";




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

            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {


            Uri cachedUri = data.getData();
            imageUri = copyImageToInternalStorage(this, cachedUri);

            Glide.with(imgavatar.getContext())
                    .load(new File(imageUri.getPath())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.inbox) // Ảnh mặc định nếu đang load
                    .error(R.drawable.inbox) // Ảnh mặc định nếu load thất bại
                    .into(imgavatar);        }
    }


    private Uri copyImageToInternalStorage(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream == null) {
                Toast.makeText(context, "Không thể mở InputStream", Toast.LENGTH_SHORT).show();
                return null;
            }

            // Tạo một file với tên duy nhất để tránh ghi đè
            File imageDir = new File(context.getFilesDir(), "images");
            if (!imageDir.exists()) {
                imageDir.mkdir();
            }
            File tempFile = new File(imageDir, "image_" + System.currentTimeMillis() + ".jpg");

            OutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

            Uri fileUri = Uri.fromFile(tempFile);
            return fileUri;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi khi sao chép ảnh", Toast.LENGTH_SHORT).show();
            return null;
        }
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