package com.example.tlucontactfinal.Userui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontactfinal.DatabaseHelper;
import com.example.tlucontactfinal.R;
import com.example.tlucontactfinal.adapter.adapterCbnv;
import com.example.tlucontactfinal.model.cbnv;

import java.util.ArrayList;

public class Danhbacbnv extends AppCompatActivity implements adapterCbnv.OnItemClickListener {
    EditText edtsearch;
    RecyclerView recyclerView;
    adapterCbnv adapter;
    ArrayList<cbnv> listcbnv;
    DatabaseHelper helper;
    Toolbar toolbar;
    ImageView imgaddcbnv;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danhbacbnv);
        anhxa();
        Actionbar();
        Intent intent1 = getIntent();
        user = intent1.getStringExtra("user");
        if (user == null){
            user = "user";
        }
        if (user.equals("admin")) {
            imgaddcbnv.setVisibility(View.VISIBLE);
        } else {
            imgaddcbnv.setVisibility(View.GONE);
        }

        adapter.setOnItemClick(this);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query =edtsearch.getText().toString().trim();
                filterList(s.toString());



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgaddcbnv.setOnClickListener(v -> {
            Intent intent = new Intent(Danhbacbnv.this, Themcbnv.class);
            startActivity(intent);
        });

    }
    private void filterList(String query) {

        if (query.isEmpty()) {
            adapter.updateList(listcbnv);

        } else {
            ArrayList<cbnv> filteredList = new ArrayList<>();
            for (cbnv cb : listcbnv) {
                if (cb.getTencb().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(cb);
                }
            }
            adapter.updateList(filteredList);
        }

    }


    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void anhxa() {
        imgaddcbnv = findViewById(R.id.imgaddcbnv);
        toolbar = findViewById(R.id.tbdanhbacbnv);
        edtsearch = findViewById(R.id.edttimkiemcbnv);
        recyclerView = findViewById(R.id.rcvcbnv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listcbnv = new ArrayList<>();
        helper = new DatabaseHelper(this);
        listcbnv = helper.getAllCbnv();
        adapter = new adapterCbnv(listcbnv);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onAvatarClick(int position) {
        Intent intent = new Intent(Danhbacbnv.this, Chitietcbnv.class);
        intent.putExtra("userrole", user );
        intent.putExtra("cbnv", listcbnv.get(position));
        startActivity(intent);

    }

    @Override
    public void onCallClick(int position) {
        String sdt = listcbnv.get(position).getSdt();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + sdt));
        startActivity(intent);


    }
}