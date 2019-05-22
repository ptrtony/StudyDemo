package com.zhaofan.studaydemo.scrollview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.zhaofan.studaydemo.R;

/**
 * recyclerview内有scrollview滑动冲突
 */
public class RecyclerScrollViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_scroll_view);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemDataAdapter(this));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                if (motionEvent!=null){
                    View childView = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                    if (childView!=null){
                        ItemDataAdapter.ViewHolder itemDataAdapter = (ItemDataAdapter.ViewHolder) recyclerView.getChildViewHolder(childView);
                        recyclerView.requestDisallowInterceptTouchEvent(itemDataAdapter.isTouchNSV((int) motionEvent.getRawX(),(int) motionEvent.getRawY()));
                    }
                }
               return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }
}
