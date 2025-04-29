package com.example.bmiapp;

public class BmiSuggestion {
    private String suggestion;
    private int img_id;

    public BmiSuggestion(String suggestion, int img_id) {
        this.suggestion = suggestion;
        this.img_id = img_id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getImg_id() {
        return img_id;
    }
}
