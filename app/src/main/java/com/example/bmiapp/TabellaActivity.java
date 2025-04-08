package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TabellaActivity extends AppCompatActivity {
    double BMI;
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

        // get the intent
        Intent intent = getIntent();
        BMI = intent.getDoubleExtra("bmi", 0.0);

        // put the BMI in the textview
        TextView textViewBMI = findViewById(R.id.textViewBMI);
        textViewBMI.setText(String.format("%.2f", BMI));
    }
}
