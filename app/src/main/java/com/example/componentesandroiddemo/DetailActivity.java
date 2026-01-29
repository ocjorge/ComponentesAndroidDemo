package com.example.componentesandroiddemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txt = findViewById(R.id.txtDetail);
        int id = getIntent().getIntExtra("PARAMETRO_ID", -1);
        txt.setText("ID recibido: " + id);
    }
}
