package com.lesgirls;

import android.content.Loader;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lesgirls.fragments.main.FollowingFragment;
import com.lesgirls.fragments.main.SeenFragment;
import com.lesgirls.fragments.main.YouFragment;
import com.lesgirls.network.AddActivityLoader;
import com.lesgirls.network.GetActivityLoader;
import com.lesgirls.network.ProfileLoader;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.GetActivityResponse;
import com.lesgirls.network.model.entity.ListDataActivity;

import java.util.ArrayList;

public class ActActivity extends BaseActivity implements
        FollowingFragment.OnFollowind,
        YouFragment.OnYouListener,
        SeenFragment.OnSeenListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<Fragment> arr;
    private ListDataActivity dataAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataAct = new ListDataActivity();

        arr = new ArrayList<Fragment>();
        arr.add(0, FollowingFragment.newInstance(getSettings().getUserID()));
        arr.add(1, YouFragment.newInstance(getSettings().getUserID()));
        arr.add(2, SeenFragment.newInstance(getSettings().getUserID()));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), arr);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getLoaderManager().initLoader(R.id.getactivity_loader, Bundle.EMPTY, this);

    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_act, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    */
    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id){
            case R.id.getactivity_loader:{
                JsonObject emp = new JsonObject();
                emp.addProperty("emptyField", 1);
                JsonObject obj = new JsonObject();
                obj.add("Activity", emp);
                obj.addProperty("token", this.getSettings().getToken());
                return  new GetActivityLoader(this, obj);
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        switch (loader.getId()){
            case R.id.getactivity_loader:{
                int status = ((GetActivityResponse) data).getStatus();
                if(status == 200){
                    dataAct = ((GetActivityResponse) data).getData();
                    ((FollowingFragment)arr.get(0)).setData(dataAct);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
    }

    @Override
    public void onCard() {

    }

    @Override
    public void onSeen() {

    }

    @Override
    public void onYou() {

    }

    public ListDataActivity getData(){return dataAct;}

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> list;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.following);
                case 1:
                    return getResources().getString(R.string.you);
                case 2:
                    return getResources().getString(R.string.seen);
            }
            return null;
        }
    }
}
