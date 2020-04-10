package com.example.tonyn.viewpager;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tonyn on 2017/4/2.
 */

public class ViewPagerAdapter extends PagerAdapter {

    List<View> list;
    List<String> titlelist;

    public ViewPagerAdapter(List<View> list,List<String> titlelist){
        this.list = list;
        this.titlelist = titlelist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
