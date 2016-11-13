package com.lesgirls.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesgirls.LesGirls;
import com.lesgirls.MainActivity;
import com.lesgirls.ProfileActivity;
import com.lesgirls.R;
import com.lesgirls.network.model.entity.AppUser;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.AttachmentDeserializer;
import com.lesgirls.network.model.entity.AttachmentSerializer;
import com.lesgirls.network.model.entity.Country;
import com.lesgirls.network.model.entity.CountryDeserializer;
import com.lesgirls.network.model.entity.CountrySerializer;
import com.lesgirls.network.model.entity.RandomUserList;
import com.lesgirls.network.model.entity.User;
import com.lesgirls.network.model.entity.UserDeserializer;
import com.lesgirls.network.model.entity.UserSerializer;
import com.lesgirls.utils.ScreenUtils;
import com.lesgirls.views.DividerItemDecoration;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RandomUserFragment extends Fragment {
    private static final String TAG = "RandomUserFragment";
    private OnRandomUsersListener listener;
    private RecyclerView rvRandom;
    private RandomUserAdapter adapter;
    public RandomUserFragment() {
    }
    public static RandomUserFragment newInstance() {
        RandomUserFragment fragment = new RandomUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_random_user, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        rvRandom = (RecyclerView) view.findViewById(R.id.rvRandom);
        adapter = new RandomUserAdapter(getActivity(), ((MainActivity)getActivity()).getList(), rvRandom.getWidth()/2);
/*
        GreedoLayoutManager layoutManager = new GreedoLayoutManager(adapter);

        rvRandom.setLayoutManager(layoutManager);
        rvRandom.setAdapter(adapter);
        layoutManager.setMaxRowHeight(300);
        int spacing = ScreenUtils.DpToPixel(4, getActivity());
        rvRandom.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
*/

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvRandom.setLayoutManager(layoutManager);
        rvRandom.addItemDecoration(new DividerItemDecoration(getActivity()));
        rvRandom.setAdapter(adapter);


    }
        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRandomUsersListener) {
            listener = (OnRandomUsersListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public interface OnRandomUsersListener {
        void onView(Uri uri);
    }

    public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.ViewHolder>{
        private final int cellWidth;
        private Context context;
        private RandomUserList list;
        private float ratio;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public AppUser user;
            public ImageView ivPhoto;
            public CardView cv;
            public long id;
            public ViewHolder(View view) {
                super(view);
                ivPhoto = (ImageView) view.findViewById(R.id.ivRandom);
                cv = (CardView) view.findViewById(R.id.card_view);
            }
        }
        public RandomUserAdapter(Context context, RandomUserList list, int cellWidth) {
            this.context = context;
            this.list = list;
            Long ll = new Long(Math.round(ScreenUtils.getScreenWidth(getActivity())/2.5));
            this.cellWidth = ll.intValue();//cellWidth;
            this.context = context;
        }
        @Override
        public RandomUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_user_adapter, parent, false);
            final ViewHolder vh = new ViewHolder(view);

            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int viewWidth = view.getWidth();
                        int viewHeight = view.getHeight();
                    }
                });
            }
            return vh;
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final AppUser item = this.list.get(position);
            holder.user = item;
            long l = item.getAttachment().getID();
            final int w = item.getAttachment().getWidth();
            final int h = item.getAttachment().getHeight();
            ratio = ((float)w)/((float)h);

            if(l!=0) {
                String token = ((MainActivity) getActivity()).getSettings().getToken();
                String url = LesGirls.base_url + "downloadImage/?token=" + token + "&Attachment[id]=" + String.valueOf(l);
                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        //ViewGroup.LayoutParams imageLayoutParams = holder.ivPhoto.getLayoutParams();
                        //imageLayoutParams.width = cellWidth;
                        //imageLayoutParams.height = h * cellWidth / w;
                        //holder.ivPhoto.setLayoutParams(imageLayoutParams);
                        holder.ivPhoto.setImageBitmap(bitmap);
                        //ViewGroup.LayoutParams p = holder.cv.getLayoutParams();
                        //p.width  = holder.ivPhoto.getWidth();
                        //p.height = holder.ivPhoto.getHeight();
                        //holder.cv.setLayoutParams(p);
                    }
                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {}
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {}
                };
                Picasso.with(getActivity()).load(url).resize(cellWidth,h*cellWidth/w).into(holder.ivPhoto);
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(User.class, new UserDeserializer())
                                .registerTypeAdapter(User.class, new UserSerializer())
                                .registerTypeAdapter(Country.class, new CountryDeserializer())
                                .registerTypeAdapter(Country.class, new CountrySerializer())
                                .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                                .registerTypeAdapter(Attachment.class, new AttachmentSerializer())
                                .create();
                        String sJson = gson.toJson(item);
                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                        intent.putExtra("user", sJson);
                        startActivity(intent);
                    }
                });
                Log.i("TOKEN", url);
                Log.i(TAG, "CardView: w:"+holder.cv.getWidth()+" h:"+holder.cv.getHeight());
                Log.i(TAG, "ImageView: w:"+holder.ivPhoto.getWidth()+" h:"+holder.ivPhoto.getHeight());
                Log.i(TAG, "Image: w:"+w+" h:"+h);
            }
            else holder.ivPhoto.setImageResource(R.drawable.ic_default_photo);
        }

        public int getItemCount() {
            return ((list == null)?0:list.size());
        }
    }
}
