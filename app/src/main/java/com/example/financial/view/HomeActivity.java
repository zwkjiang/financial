package com.example.financial.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.accout.view.AccountFragment;
import com.example.finalce.view.FinalceFragment;
import com.example.financial.R;
import com.example.financial.adapter.FragmentViewPagerAdapter;
import com.example.home.view.HomeFragment;
import com.example.more.view.MoreFragment;
import com.example.wiget.TitleBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout dlMain;
    private TitleBar tbMain;
    private ViewPager vpMain;
    private List<Fragment> fragments;
    private BottomNavigationView bnvBottomber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        bnvBottomber.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_bottombar_home:
                        vpMain.setCurrentItem(0);
                        setLeftRightVisible(true);
                        return true;
                    case R.id.menu_bottombar_finalce:
                        vpMain.setCurrentItem(1);
                        setLeftRightVisible(false);
                        return true;
                    case R.id.menu_bottombar_account:
                        vpMain.setCurrentItem(2);
                        tbMain.setTitle("个人中心");
                        setLeftRightVisible(false);
                        return true;
                    case R.id.menu_bottombar_more:
                        vpMain.setCurrentItem(3);
                        setLeftRightVisible(false);
                        return true;
                }
                return false;
            }
        });
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                bnvBottomber.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tbMain.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {
                dlMain.openDrawer(Gravity.LEFT);
            }

            @Override
            public void rightClick(View view) {
                Toast.makeText(HomeActivity.this, "right click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FinalceFragment());
        fragments.add(new AccountFragment());
        fragments.add(new MoreFragment());
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
    }

    private void initView() {
        dlMain = (DrawerLayout) findViewById(R.id.dl_main);
        tbMain = (TitleBar) findViewById(R.id.tb_main);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        bnvBottomber = (BottomNavigationView) findViewById(R.id.bnv_bottomber);
        dlMain.setScrimColor(Color.TRANSPARENT);
    }

    private void setLeftRightVisible(boolean isVisible){
        tbMain.setLeftVisible(isVisible);
        tbMain.setRightVisible(isVisible);
    }
}
