package com.example.a32150.a2017110902;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {

    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ib = (ImageButton)findViewById(R.id.imageButton);


    }
}
