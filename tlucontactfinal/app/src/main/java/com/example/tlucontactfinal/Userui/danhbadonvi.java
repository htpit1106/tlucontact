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
import com.example.tlucontactfinal.adapter.adapterDonvi;
import com.example.tlucontactfinal.model.donvi;

import java.util.ArrayList;

public class danhbadonvi extends AppCompatActivity implements adapterDonvi.OnItemClickListener {

    DatabaseHelper helper;
    ArrayList<donvi> listdonvi;
    RecyclerView recyclerView;
    adapterDonvi adapter;
    Toolbar toolbar;
    EditText edtsearch;
    ImageView imgadddonvi;
    String userrole;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danhbadonvi);

        anhxa();

        Intent intent1 = getIntent();
        userrole = intent1.getStringExtra("user");
        if(userrole==null){
            userrole="user";
        }
        if (userrole.equals("admin")) {
            imgadddonvi.setVisibility(View.VISIBLE);
        } else {
            imgadddonvi.setVisibility(View.GONE);
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
        Actionbar();
        imgadddonvi.setOnClickListener(v -> {
            Intent intent = new Intent(danhbadonvi.this, Themdonvi.class);
            startActivity(intent);
        });

    }

    protected void onResume() {
        super.onResume();
        loadDanhBa();  // Gọi lại hàm load danh bạ
    }

    private void loadDanhBa() {
        listdonvi = helper.getAllDonvi();
        adapter.updateList(listdonvi);
        adapter.notifyDataSetChanged();
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void filterList(String query) {

        if (query.isEmpty()) {
            adapter.updateList(listdonvi);

        } else {
            ArrayList<donvi> filteredList = new ArrayList<>();
            for (donvi dv : listdonvi) {
                if (dv.getTendv().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(dv);
                }
            }
            adapter.updateList(filteredList);
        }

    }


    private void anhxa() {
        imgadddonvi = findViewById(R.id.imgadddonvi);
        edtsearch = findViewById(R.id.edttimkiemdonvi);
        toolbar = findViewById(R.id.tbdanhbadonvi);
        recyclerView = findViewById(R.id.rcvdonvi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listdonvi = new ArrayList<>();
        helper = new DatabaseHelper(this);
        listdonvi = helper.getAllDonvi();
        adapter = new adapterDonvi(listdonvi);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onAvatarClick(int position) {
        Intent intent = new Intent(danhbadonvi.this, ChitietDonvi.class);
        intent.putExtra("donvi", listdonvi.get(position));
        intent.putExtra("userrole", userrole);
        startActivity(intent);



    }

    @Override
    public void onCallClick(int position) {
        String sdt = listdonvi.get(position).getSdt();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + sdt));
        startActivity(intent);

    }
}