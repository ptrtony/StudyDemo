package com.zhaofan.studaydemo.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhaofan.studaydemo.R;

public class AnimationActivity extends AppCompatActivity {
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        view = findViewById(R.id.view);
        view.animate().setDuration(3000)
                .scaleX(2f)
                .scaleY(2f)
                .translationX(600)
                .start();
    }
}
