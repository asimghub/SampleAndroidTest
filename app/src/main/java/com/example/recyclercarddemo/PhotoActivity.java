package com.example.recyclercarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
