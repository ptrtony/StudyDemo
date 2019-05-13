package com.zhaofan.studaydemo.controlview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zhaofan.studaydemo.R;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private static final String TAG = CoordinatorLayoutActivity.class.getSimpleName();
    private String image_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556179853825&di=7ac5773a4b25a732fba32d061ee567be&imgtype=0&src=http%3A%2F%2Fimg.qdaily.com%2Farticle%2Farticle_show%2F201606071908333Q4WUigp8NhXkYql.jpg%3FimageMogr2%2Fauto-orient%2Fthumbnail%2F%2521755x450r%2Fgravity%2FCenter%2Fcrop%2F755x450%2Fquality%2F85%2Fformat%2Fjpg%2Fignore-error%2F1";
    private List<Fragment> fragments = new ArrayList<>();
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        ImageView imageView = findViewById(R.id.imageView);
        ImageLoaderUtils.Builder(this).ImageLoader(imageView,image_url);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ViewPager viewPager = findViewById(R.id.id_viewpager);
        TabLayout tableLayout = findViewById(R.id.tab_layout);
        fragments.add(BlankFragment.newInstance(1));
        fragments.add(BlankFragment.newInstance(2));
        fragments.add(BlankFragment.newInstance(3));
        fragments.add(BlankFragment.newInstance(4));
        fragments.add(BlankFragment.newInstance(5));
        fragments.add(BlankFragment.newInstance(6));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));
        for (int i=0;i<fragments.size();i++){
            tableLayout.addTab(tableLayout.newTab().setText("Tab"+(i+1)));
        }
        tableLayout.setupWithViewPager(viewPager);


//        mDrawerLayout = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.navigation);
//        if (navigationView!=null){
//            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    Snackbar.make(mDrawerLayout,menuItem.getTitle()+"pressed",Snackbar.LENGTH_LONG).show();
//                    menuItem.setChecked(true);
//                    mDrawerLayout.closeDrawers();
//                    mDrawerLayout.setVisibility(View.GONE);
//
//                    return true;
//                }
//            });
//        }


//        View bottomSheet = findViewById(R.id.design_bottom_sheet);
//        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View view, int i) {
//                Log.d(TAG,"onSlide: "+i);
//            }
//
//            @Override
//            public void onSlide(@NonNull View view, float v) {
//                Log.d(TAG,"onStateChanged: "+v);
//            }
//        });

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                mDrawerLayout.setVisibility(View.VISIBLE);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }



    public class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments ;
        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
