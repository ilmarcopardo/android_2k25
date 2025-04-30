package com.example.bmiapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TabellaActivity extends AppCompatActivity implements OnBmiClickListener{
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
        bmiList.add(new BmiCategory("Sottopeso", "< 18.5", R.drawable.stickman));
        bmiList.add(new BmiCategory("Normopeso", "18.5 - 24.9", R.drawable.stickman));
        bmiList.add(new BmiCategory("Sovrappeso", "25 - 29.9", R.drawable.stickman));
        bmiList.add(new BmiCategory("Obesità I grado", "30 - 34.9", R.drawable.stickman));
        bmiList.add(new BmiCategory("Obesità II grado", "35 - 39.9", R.drawable.stickman));
        bmiList.add(new BmiCategory("Obesità III grado", "≥ 40", R.drawable.stickman));

        List<BmiSuggestion> suggestionList = new ArrayList<>();
        suggestionList.add(new BmiSuggestion("Sottopeso", R.drawable.bmi_image));
        suggestionList.add(new BmiSuggestion("Normopeso", R.drawable.bmi_image));
        suggestionList.add(new BmiSuggestion("Sovrappeso", R.drawable.bmi_image));
        suggestionList.add(new BmiSuggestion("Obesità I grado", R.drawable.bmi_image));
        suggestionList.add(new BmiSuggestion("Obesità II grado", R.drawable.bmi_image));
        suggestionList.add(new BmiSuggestion("Obesità III grado", R.drawable.bmi_image));


        // get the intent
        Intent intent = getIntent();
        BMI = intent.getDoubleExtra("bmi", 0.0);

        // put the BMI in the textview
        TextView textViewBMI = findViewById(R.id.textViewBMI);
        textViewBMI.setText(String.format("%.2f", BMI));

        // handle recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.bmiRecyclerView);
        adapter = new BmiItemAdapter(this, bmiList, suggestionList, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onBmiClick(BmiSuggestion bmiSuggestion) {
        SuggestionFragment suggestionFragment = SuggestionFragment.newInstance(bmiSuggestion);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, suggestionFragment).addToBackStack(null).commit();
    }
}
