package com.example.tlucontactfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btndbdonvi, btndbcbnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btndbdonvi = findViewById(R.id.btndbdonvi);
        btndbcbnv = findViewById(R.id.btndbcbnv);

        btndbdonvi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, danhbadonvi.class);
            startActivity(intent);
        });

        btndbcbnv.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Danhbacbnv.class);
            startActivity(intent);
        });

    }
}