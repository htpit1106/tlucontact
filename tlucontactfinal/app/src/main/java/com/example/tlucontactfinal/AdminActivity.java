package com.example.tlucontactfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlucontactfinal.Userui.Danhbacbnv;
import com.example.tlucontactfinal.Userui.danhbadonvi;

public class AdminActivity extends AppCompatActivity {
    Button btndbdonviad, btndbcbnvad;
    private  static final String asuser = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        btndbdonviad = findViewById(R.id.btndbdonviad);
        btndbcbnvad = findViewById(R.id.btndbcbnvad);
        btndbdonviad.setOnClickListener(view -> {

            Intent intent = new Intent(AdminActivity.this, danhbadonvi.class);
            intent.putExtra("user",asuser);
            startActivity(intent);
        });
        btndbcbnvad.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, Danhbacbnv.class);
            intent.putExtra("user",asuser);
            startActivity(intent);
        });


    }
}