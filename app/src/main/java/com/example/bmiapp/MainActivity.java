package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int peso;
    int altezza;
    double BMI;

    // contextual action bar //
    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.action_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.AMCopia){
                Toast.makeText(MainActivity.this, "Copiato!", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.AMIncolla){
                Toast.makeText(MainActivity.this, "Incollato!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionModeCallback = null;
        }
    };
    ActionMode mActionMode;

    // appbar //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_item){
            Toast.makeText(MainActivity.this, "Settings!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.favorites_item){
            Toast.makeText(MainActivity.this, "Favorites!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText pesoEditText = findViewById(R.id.editTextPeso);
        EditText altezzaEditText = findViewById(R.id.editTextAltezza);
        TextView textViewBMIResult = findViewById(R.id.textViewBMIResult);
        Button calcolaBMIButton = findViewById(R.id.buttonCalcolaBMI);
        Button mostraTabellaButton = findViewById(R.id.buttonMostraTabella);

        // restore BMI value //
        if (savedInstanceState != null){
            BMI = savedInstanceState.getDouble("bmi");
            textViewBMIResult.setVisibility(View.VISIBLE);
            textViewBMIResult.setText(String.format("%.2f", BMI));
        }

        calcolaBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peso = Integer.parseInt(pesoEditText.getText().toString());
                altezza = Integer.parseInt(altezzaEditText.getText().toString());

                // calcolo del BMI
                BMI = peso / Math.pow((altezza/100.0),2);
                textViewBMIResult.setVisibility(View.VISIBLE);
                textViewBMIResult.setText(String.format("%.2f", BMI));
            }
        });

        // contextual action bar on the input EditText for weight
        pesoEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) return false;
                else {
                    mActionMode = startActionMode(mActionModeCallback);
                    v.setSelected(true);
                    return true;
                }
            }
        });

        // popup menu on the input EditText for height
        altezzaEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, altezzaEditText);
                getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.replyAllItem) {
                            Toast.makeText(MainActivity.this, "Reply All!", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (item.getItemId() == R.id.forwardItem) {
                            Toast.makeText(MainActivity.this, "Forward!", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popup.show();
                return true;
            }
        });

        // launch the next activity
        mostraTabellaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabellaActivity.class);
                intent.putExtra("bmi", BMI);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BMI != 0.0){
            outState.putDouble("bmi", BMI);
        }
    }
}