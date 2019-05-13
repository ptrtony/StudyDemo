package com.zhaofan.studaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zhaofan.studaydemo.R;

public class OnSaveStoreActivity extends AppCompatActivity {
    TextView mTextView;
    private static final String TAG = "OnSaveStoreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_save_store);
        mTextView = findViewById(R.id.tv_receive_data);

        if (savedInstanceState!=null){
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG,"[onCreate]restore extra_test:"+test);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        outState.putString("extra_test","test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG,"[onRestoreInstanceState]restore extra_test:"+test);
    }
}
