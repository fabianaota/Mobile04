package com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerDigitalHouseAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;
    List<String> tituloList;

    public ViewPagerDigitalHouseAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tituloList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tituloList = tituloList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tituloList.get(position);
    }
}
