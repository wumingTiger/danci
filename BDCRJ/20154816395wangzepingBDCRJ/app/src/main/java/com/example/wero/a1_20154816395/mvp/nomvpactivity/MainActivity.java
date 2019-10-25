package com.example.wero.a1_20154816395.mvp.nomvpactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wero.a1_20154816395.ProApplication;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.adapter.Home_FragmentPageAdapter;
import com.example.wero.a1_20154816395.mvp.MvpFragment;
import com.example.wero.a1_20154816395.mvp.addwordspack.AddWordsPackActivity;
import com.example.wero.a1_20154816395.mvp.chart.ChartFragment;
import com.example.wero.a1_20154816395.mvp.home.HomeFragment;
import com.example.wero.a1_20154816395.mvp.login.LoginActivity;
import com.example.wero.a1_20154816395.mvp.studycllection.StudyCollectionFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.mViewPager)  ViewPager vp;
    @BindView(R.id.stl_home)    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.toolbar)     Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;
    private ArrayList<MvpFragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "首页", "学习集", "统计"
    };
    private Home_FragmentPageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_query) {
            Intent intent = new Intent();
            intent.setClass(this, QueryWordActivity.class);
            startActivity(intent);
            return true;
        }else {
            showLogout();

        }

        return super.onOptionsItemSelected(item);
    }

    private void showLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("确定", (dialog, which) -> {
            ProApplication.USER = null;
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        });
        builder.setNegativeButton("取消", ((dialog, which) -> {
            dialog.dismiss();
        }));
        builder.setTitle("登出?");
        builder.show();
    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    private void init(){
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddWordsPackActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        mFragments.add(new HomeFragment());
        mFragments.add(new StudyCollectionFragment());
        mFragments.add(new ChartFragment());

        mAdapter = new Home_FragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp);
    }
}
