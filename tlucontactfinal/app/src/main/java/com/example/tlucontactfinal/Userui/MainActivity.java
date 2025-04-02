package com.example.tlucontactfinal.Userui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tlucontactfinal.LoginActivity;
import com.example.tlucontactfinal.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btndbdonvi, btndbcbnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btndbdonvi = findViewById(R.id.btndbdonvi);
        btndbcbnv = findViewById(R.id.btndbcbnv);
        toolbar = findViewById(R.id.tbtrangchu);
        setSupportActionBar(toolbar);

        btndbdonvi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, danhbadonvi.class);
            startActivity(intent);
        });

        btndbcbnv.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Danhbacbnv.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.account) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}