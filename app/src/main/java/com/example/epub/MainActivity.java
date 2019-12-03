package com.example.epub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.epub.adapter.SectionsPagerAdapter;
import com.example.epub.fragment.CategoriesFragment;
import com.example.epub.fragment.HomeFragment;
import com.example.epub.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpMain;
    private MenuItem bnHomeNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.bn_home_navigation);
        vpMain = findViewById(R.id.vp_main);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {

                if(bnHomeNavigation !=null){
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
        vpMain.setOnTouchListener((view, motionEvent) -> true);

        setupViewPager(vpMain);
    }

    private void setupViewPager(ViewPager vpMain) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        UserFragment userFragment = new UserFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(categoriesFragment);
        adapter.addFragment(userFragment);
        vpMain.setAdapter(adapter);
    }

}
