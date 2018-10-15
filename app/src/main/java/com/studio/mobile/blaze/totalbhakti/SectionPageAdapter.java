
package com.studio.mobile.blaze.totalbhakti;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPageAdapter extends FragmentPagerAdapter {
    private List <Fragment> fragment_list = new ArrayList<>();
    private List <String> string_list = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm) { super(fm); }

    public void AddFragmentPage(Fragment Frag , String Title){
        fragment_list.add(Frag); string_list.add(Title);
    }

    @Override
    public Fragment getItem(int position) { return fragment_list.get(position); }

    @Override
    public CharSequence getPageTitle(int position) {
        return string_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }
}
