package com.lesgirls.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.lesgirls.R;

import java.util.ArrayList;

/**
 * Created by victor on 09.07.16.
 */
public class VSPageAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private ArrayList<Fragment> list;
    public VSPageAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }
    public VSPageAdapter(Context context, FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = "";
        switch (position){
            case 0:{title = this.context.getResources().getString(R.string.activity);break;}
            case 1:{title = this.context.getResources().getString(R.string.match);break;}
            case 2:{title = this.context.getResources().getString(R.string.nearby);break;}
        }
        return title;
    }
}
