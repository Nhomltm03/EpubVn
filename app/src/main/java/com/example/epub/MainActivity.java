package com.example.epub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.epub.adapter.SectionsPagerAdapter;
import com.example.epub.fragment.CategoriesFragment;
import com.example.epub.fragment.HomeFragment;
import com.example.epub.fragment.UserFragment;
import com.example.epub.ultis.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private LockSwipeViewPager vpMain;
    private MenuItem bnHomeNavigation;
    private FrameLayout noNetworkLayout;
    private BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.bn_item_home:
                            vpMain.setCurrentItem(0);
                            break;
                        case R.id.bn_item_categories:
                            vpMain.setCurrentItem(1);
                            break;
                        case R.id.bn_item_user:
                            vpMain.setCurrentItem(2);
                            break;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }

    private void init() {
        this.initView();
        this.initPager();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initPager() {
        this.navView.setOnNavigationItemSelectedListener(mItemSelectedListener);
        this.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                if (bnHomeNavigation != null) {
                    bnHomeNavigation.setChecked(false);
                } else {
                    navView.getMenu().getItem(0).setChecked(false);
                }
                navView.getMenu().getItem(position).setChecked(true);
                bnHomeNavigation = navView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        this.vpMain.setOnTouchListener((view, motionEvent) -> false);
        this.vpMain.setClickable(false);
        this.initItem(this.vpMain);
    }

    private void initView() {
        this.setContentView(R.layout.activity_main);
        this.navView = this.findViewById(R.id.bn_home_navigation);
        this.vpMain = this.findViewById(R.id.vp_main);
        this.noNetworkLayout = this.findViewById(R.id.fl_no_network);
        this.noNetworkLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isAccessNetwork(MainActivity.this)) {
            this.noNetworkLayout.setVisibility(View.GONE);
            this.vpMain.setVisibility(View.VISIBLE);
        } else {
            this.vpMain.setVisibility(View.GONE);
            this.noNetworkLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initItem(ViewPager vpMain) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        UserFragment userFragment = new UserFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(categoriesFragment);
        adapter.addFragment(userFragment);
        vpMain.setAdapter(adapter);
    }

}
