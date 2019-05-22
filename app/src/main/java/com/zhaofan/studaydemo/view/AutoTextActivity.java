package com.zhaofan.studaydemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhaofan.studaydemo.R;

import java.util.Random;

public class AutoTextActivity extends AppCompatActivity {
    AutoScrollTextView autoScrollTextView;
    private int number = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_text);
        autoScrollTextView = findViewById(R.id.autoTextView);
    }

    public void onAutoScrollClick(View view){
        Random random = new Random(number);
        autoScrollTextView.setTargetNumber(random.nextInt());
    }
}
