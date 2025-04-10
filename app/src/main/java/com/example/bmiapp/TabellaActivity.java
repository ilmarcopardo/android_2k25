package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TabellaActivity extends AppCompatActivity {
    double BMI;
    BmiItemAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabella);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tabella), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<BmiCategory> bmiList = new ArrayList<>();
        bmiList.add(new BmiCategory("Sottopeso", "< 18.5"));
        bmiList.add(new BmiCategory("Normopeso", "18.5 - 24.9"));
        bmiList.add(new BmiCategory("Sovrappeso", "25 - 29.9"));
        bmiList.add(new BmiCategory("Obesità I grado", "30 - 34.9"));
        bmiList.add(new BmiCategory("Obesità II grado", "35 - 39.9"));
        bmiList.add(new BmiCategory("Obesità III grado", "≥ 40"));

        // get the intent
        Intent intent = getIntent();
        BMI = intent.getDoubleExtra("bmi", 0.0);

        // put the BMI in the textview
        TextView textViewBMI = findViewById(R.id.textViewBMI);
        textViewBMI.setText(String.format("%.2f", BMI));

        // handle recycler view
        recyclerView = findViewById(R.id.bmiRecyclerView);
        adapter = new BmiItemAdapter(this, bmiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
