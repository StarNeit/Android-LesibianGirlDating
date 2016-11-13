package com.lesgirls;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.fragments.profile.DualPhotoFragment;
import com.lesgirls.fragments.profile.MainProfileFragment;
import com.lesgirls.fragments.settings.MyPhotoFragment;
import com.lesgirls.network.AddActivityLoader;
import com.lesgirls.network.AddMessageLoader;
import com.lesgirls.network.BlockLoader;
import com.lesgirls.network.MyPhotoLoader;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.MyPhotoResponse;
import com.lesgirls.network.model.SimpleResponse;
import com.lesgirls.network.model.entity.AppUser;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.AttachmentDeserializer;
import com.lesgirls.network.model.entity.AttachmentSerializer;
import com.lesgirls.network.model.entity.Country;
import com.lesgirls.network.model.entity.CountryDeserializer;
import com.lesgirls.network.model.entity.CountrySerializer;
import com.lesgirls.network.model.entity.MyPhotoList;
import com.lesgirls.network.model.entity.User;
import com.lesgirls.network.model.entity.UserDeserializer;
import com.lesgirls.network.model.entity.UserSerializer;
import com.lesgirls.views.VSwiper;

import java.util.ArrayList;
import java.util.Random;

public class ProfileActivity extends BaseActivity implements MainProfileFragment.OnMainProfileListener, DualPhotoFragment.OnDualPhotoFragment {

    private String json = null;
    private AppUser user = null;
    private VSwiper pager;
    private ArrayList<Fragment> arr;
    private ProfilePageAdapter adapter;
    private MyPhotoList list;
    private boolean followed = false;
    private RelativeLayout rlBack;
    private boolean blocked = false;
    private boolean messages = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        blocked = false;
        messages = false;
        Bundle data = intent.getExtras();
        if (data != null) {
            json = data.getString("user");
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(User.class, new UserDeserializer())
                    .registerTypeAdapter(User.class, new UserSerializer())
                    .registerTypeAdapter(Country.class, new CountryDeserializer())
                    .registerTypeAdapter(Country.class, new CountrySerializer())
                    .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                    .registerTypeAdapter(Attachment.class, new AttachmentSerializer())
                    .create();
            user = gson.fromJson(json, AppUser.class);
        }
        pager = (VSwiper) findViewById(R.id.vpPager);
        arr = new ArrayList<Fragment>();
        adapter = new ProfilePageAdapter(getSupportFragmentManager(), arr);
        arr.add(0, MainProfileFragment.newInstance());
        arr.add(1, DualPhotoFragment.newInstance());
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);

        rlBack = (RelativeLayout) findViewById(R.id.rlBack);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem() == 1){
                    pager.setCurrentItem(0);
                }
                else finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_block) {
            Bundle b = new Bundle();
            b.putLong("id", user.getUser().getID());
            if(!blocked){getLoaderManager().initLoader(R.id.block_loader,b, this);blocked = true;}
            else getLoaderManager().restartLoader(R.id.block_loader,b, this);
            return true;
        }
        else if (id == R.id.action_photo) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public AppUser getUser() {
        return user;
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.myphoto_loader: {
                JsonObject att = new JsonObject();
                att.addProperty("emptyField", 1);
                JsonObject obj = new JsonObject();
                obj.add("Attachment", att);
                obj.addProperty("token", getSettings().getToken());
                return new MyPhotoLoader(this, obj);
            }
            case R.id.addactivity_loader:{
                JsonObject act = new JsonObject();
                act.addProperty("user_to_id", args.getLong("toUser"));
                act.addProperty("status", args.getInt("activityID"));
                JsonObject obj = new JsonObject();
                obj.add("Activity", act);
                obj.addProperty("token", getSettings().getToken());
                Log.i("TOKEN", obj.toString());
                return new AddActivityLoader(this, obj);
            }
            case R.id.block_loader:{
                JsonObject us = new JsonObject();
                us.addProperty("user_to_id", args.getLong("id"));
                JsonObject obj = new JsonObject();
                obj.add("UserBlock", us);
                obj.addProperty("token", getSettings().getToken());
                Log.i("TOKEN", obj.toString());
                return new BlockLoader(this, obj);
            }
            case R.id.addmessage_loader:{
                JsonObject mess = new JsonObject();
                mess.addProperty("user_to_id", args.getLong("id"));
                mess.addProperty("message", args.getString("message"));
                JsonObject obj = new JsonObject();
                obj.add("Message", mess);
                obj.addProperty("token", getSettings().getToken());
                Log.i("TOKEN", obj.toString());
                return new AddMessageLoader(this, obj);
            }

            default: return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        switch (loader.getId()) {
            case R.id.myphoto_loader: {
                int status = ((MyPhotoResponse) data).getStatus();
                if (status == 200) {
                    list = ((MyPhotoResponse) data).getData();
                    Random rand = new Random();
                    int ii = rand.nextInt(list.size());
                    int jj = rand.nextInt(list.size());
                    Attachment at1 = list.get(ii);
                    Attachment at2 = list.get(jj);
                    String url1 = LesGirls.base_url + "downloadImage/?token=" + getSettings().getToken() + "&Attachment[id]=" + String.valueOf(at1.getID());
                    String url2 = LesGirls.base_url + "downloadImage/?token=" + getSettings().getToken() + "&Attachment[id]=" + String.valueOf(at2.getID());
                    DualPhotoFragment fr = (DualPhotoFragment) arr.get(1);
                    fr.setURL(url1, url2);
                }
                break;
            }
            case R.id.addactivity_loader:{
                int status = ((SimpleResponse) data).getStatus();
                String sMessage = ((SimpleResponse) data).getMessage();
                if(status != 200) Toast.makeText(this,"Status:"+status+" - "+sMessage,Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.block_loader:{
                int status = ((SimpleResponse) data).getStatus();
                String sMessage = ((SimpleResponse) data).getMessage();
                if(status != 200) Toast.makeText(this,"Status:"+status+" - "+sMessage,Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.addmessage_loader:{
                int status = ((SimpleResponse) data).getStatus();
                String sMessage = ((SimpleResponse) data).getMessage();
                if(status != 200) Toast.makeText(this,"Status:"+status+" - "+sMessage,Toast.LENGTH_LONG).show();
                break;
            }

        }
        loader.reset();
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }
    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void onRightClick() {
        pager.setCurrentItem(1);
        Bundle b = new Bundle();
        b.putLong("id", user.getUser().getID());
        getLoaderManager().initLoader(R.id.myphoto_loader,b,this);
    }

    @Override
    public void onFollow(boolean set, long toUser) {
        Bundle b = new Bundle();
        b.putLong("toUser",toUser);
        b.putInt("activityID", 1);
        if(set) {
            if (!followed) {getLoaderManager().initLoader(R.id.addactivity_loader, b, this);followed = true;}
            else getLoaderManager().restartLoader(R.id.addactivity_loader, b, this);
        }
    }

    @Override
    public void onLike(boolean set, long toUser) {
        Bundle b = new Bundle();
        b.putLong("toUser",toUser);
        b.putInt("activityID", 2);
        if(set) {
            if (!followed) {getLoaderManager().initLoader(R.id.addactivity_loader, b, this);followed = true;}
            else getLoaderManager().restartLoader(R.id.addactivity_loader, b, this);
        }
    }

    @Override
    public void onAddMessage(long userUD, String message) {
        Bundle b = new Bundle();
        b.putLong("id", userUD);
        b.putString("message",message);
        if(!messages){getLoaderManager().initLoader(R.id.addmessage_loader,b,this);messages = true;}
        else getLoaderManager().restartLoader(R.id.addmessage_loader,b,this);
    }

    @Override
    public void onDualPhoto() {

    }

    @Override
    public void onDualBack() {

    }

    public class ProfilePageAdapter extends FragmentStatePagerAdapter {
        private Context context;
        private ArrayList<Fragment> list;

        public ProfilePageAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        public ProfilePageAdapter(Context context, FragmentManager fm, ArrayList<Fragment> list) {
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

    }
}