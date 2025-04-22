package com.example.bmiapp;

import android.media.Image;
import android.text.style.ImageSpan;

public class BmiCategory {
    private String categoria;
    private String range;
    private int immagineResId;

    public BmiCategory(String categoria, String range, int immagineResId) {
        this.categoria = categoria;
        this.range = range;
        this.immagineResId = immagineResId;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getRange() {
        return range;
    }

    public int getImmagineResId(){
        return immagineResId;
    }
}
