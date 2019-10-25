package com.example.wero.a1_20154816395.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.wero.a1_20154816395.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wero on 18-4-19.
 */

public class Home_FragmentPageAdapter extends FragmentPagerAdapter {
    private List<MvpFragment> mFragments;          //fragment
    private String[] mTitles;

    public Home_FragmentPageAdapter(FragmentManager fmm, ArrayList<MvpFragment> fms, String[] titles) {
        super(fmm);
        this.mFragments = fms;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
