package com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerDigitalHouseAdapter extends FragmentStatePagerAdapter {

    // Listagem com Fragments a serem exibidos pelo ViewPager
    List<Fragment> fragmentList;

    // Listagem com os títulos de cada fragment a serem exibidos no TabLayout
    List<String> tituloList;

    public ViewPagerDigitalHouseAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tituloList) {
        super(fm);
        // No construtor, recebemos a listagem de fragments e de titulos como parâmetro
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
    // Para que o titulo apareça corretamente no TabLayout, é necessário sobrescrever este método
    public CharSequence getPageTitle(int position) {
        return tituloList.get(position);
    }
}
