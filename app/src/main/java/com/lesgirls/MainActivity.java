package com.lesgirls;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesgirls.fragments.main.ActivityFragment;
import com.lesgirls.fragments.main.MatchFragment;
import com.lesgirls.fragments.main.NearbyFragment;
import com.lesgirls.fragments.main.RandomUserFragment;
import com.lesgirls.network.Random10Loader;
import com.lesgirls.network.Random10Response;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.entity.AppUser;
import com.lesgirls.network.model.entity.RandomUserList;
import com.lesgirls.views.VSPageAdapter;
import com.lesgirls.views.VSwiper;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        ActivityFragment.OnActivityListener, MatchFragment.OnMatchListener, NearbyFragment.OnNearbyListener, RandomUserFragment.OnRandomUsersListener {
    private TextView tvFirstName;
    private TextView tvMiddleName;
    private TextView tvLastname;
    private TextView tvName;
    private TextView tvAvatarURI;
    private TextView tvToken;
    private ViewPager vp;
    private ArrayList<Fragment> arr;
    private VSPageAdapter adapter;
    private VSwiper pages;
    private RelativeLayout rlActivity;
    private RelativeLayout rlMatch;
    private RelativeLayout rlNearby;
    private RandomUserList list= null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pages = (VSwiper) findViewById(R.id.vpPager);
        arr = new  ArrayList<Fragment>();
        arr.add(0, ActivityFragment.newInstance());
        arr.add(1, MatchFragment.newInstance());
        arr.add(2, NearbyFragment.newInstance());
        adapter = new VSPageAdapter(this, getSupportFragmentManager(),arr);
        pages.setAdapter(adapter);
        rlActivity = (RelativeLayout) findViewById(R.id.rlActivity);
        rlActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pages.setCurrentItem(0);
                Intent intent = new Intent(MainActivity.this, ActActivity.class);
                startActivity(intent);
            }
        });
        rlMatch = (RelativeLayout) findViewById(R.id.rlMatch);
        rlMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pages.setCurrentItem(1);
            }
        });
        rlNearby = (RelativeLayout) findViewById(R.id.rlNearby);
        rlNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pages.setCurrentItem(2);
            }
        });

        Log.i("TOKEN", getSettings().getToken());

        Bundle b = new Bundle();
        b.putLong("id", getSettings().getUserID());
        b.putString("token", getSettings().getToken());
        getLoaderManager().initLoader(R.id.random10_loader, b, MainActivity.this);
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.imGlobal) {
        } else if (id == R.id.imMessage) {
        } else if (id == R.id.imNotification) {
        } else if (id == R.id.imSetting) {
            startActivity(new Intent(this, SettingsActivity.class));
        }   else if (id == R.id.imMore) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id){
            case R.id.random10_loader:{
                long userID = args.getLong("id");
                String userToken = args.getString("token");
                return new Random10Loader(this, userID, userToken);
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        switch (loader.getId()) {
            case R.id.random10_loader:{
                int status = ((Random10Response)data).getStatus();
                if(status == 200){
                    list = ((Random10Response)data).getData();
                    for(AppUser u : list){
                        Log.i("TOKEN", ""+u.getAttachment().getID());
                    }
                    arr.add(3, RandomUserFragment.newInstance());
                    adapter.notifyDataSetChanged();
                    pages.setCurrentItem(3);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }

    @Override
    public void onActivity(Uri uri) {

    }

    @Override
    public void onMatch(Uri uri) {

    }

    @Override
    public void onNearby(Uri uri) {

    }

    @Override
    public void onView(Uri uri) {

    }

    public RandomUserList getList() {
        return list;
    }

    public void setList(RandomUserList list) {
        this.list = list;
    }
}
