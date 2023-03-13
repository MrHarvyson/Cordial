package com.josec.cordial.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.josec.cordial.R;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();
    }
}