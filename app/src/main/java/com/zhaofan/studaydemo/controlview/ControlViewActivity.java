package com.zhaofan.studaydemo.controlview;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.zhaofan.studaydemo.R;

public class ControlViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contral_view);
    }

    public void onSnackbarClick(View view){
        Snackbar.make(view,"Here's a Snackbar",Snackbar.LENGTH_SHORT)
                .setAction("Action",null)
                .show();
    }


    public void onTableLayoutClick(View view){
        startActivity(new Intent(this,TabLayoutActivity.class));
    }

    public void onDrawLayoutClick(View view){
        startActivity(new Intent(this,DrawActivity.class));
    }


    public void onCoordinatorLayoutClick(View view){
        startActivity(new Intent(this,CoordinatorLayoutActivity.class));
    }
}
