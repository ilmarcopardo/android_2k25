package com.example.bmiapp;

public class BmiCategory {
    private String categoria;
    private String range;

    public BmiCategory(String categoria, String range) {
        this.categoria = categoria;
        this.range = range;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getRange() {
        return range;
    }
}
