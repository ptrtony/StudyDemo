package com.zhaofan.studaydemo.controlview;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.zhaofan.studaydemo.R;
import java.util.ArrayList;
import java.util.List;


public class TabLayoutActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        ViewPager viewPager = findViewById(R.id.id_viewpager);
        TabLayout tableLayout = findViewById(R.id.tab_layout);

        fragments.add(BlankFragment.newInstance(1));
        fragments.add(BlankFragment.newInstance(2));
        fragments.add(BlankFragment.newInstance(3));
        fragments.add(BlankFragment.newInstance(4));
        fragments.add(BlankFragment.newInstance(5));
        fragments.add(BlankFragment.newInstance(6));
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));
        for (int i=0;i<fragments.size();i++){
            tableLayout.addTab(tableLayout.newTab().setText("Tab"+(i+1)));
        }
        tableLayout.setupWithViewPager(viewPager);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments ;
        public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab"+(position+1);
        }
    }
}
