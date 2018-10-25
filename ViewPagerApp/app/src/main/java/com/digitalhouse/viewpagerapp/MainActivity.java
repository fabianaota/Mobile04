package com.digitalhouse.viewpagerapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digitalhouse.viewpagerapp.adapter.ColorViewPagerAdapter;
import com.digitalhouse.viewpagerapp.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager_id);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ColorFragment.getInstance("Fragment 1", R.color.colorPrimary));
        fragmentList.add(ColorFragment.getInstance("Fragment 2", R.color.colorAccent));
        fragmentList.add(ColorFragment.getInstance("Fragment 3", R.color.colorPrimaryDark));
        fragmentList.add(ColorFragment.getInstance("Novo Fragment", R.color.colorAccent));

        ColorViewPagerAdapter adapter = new ColorViewPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);

        TabLayout tabLayout = findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(2);

    }
}
