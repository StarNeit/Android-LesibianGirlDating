package com.lesgirls.fragments.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lesgirls.LesGirls;
import com.lesgirls.R;
import com.lesgirls.SettingsActivity;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.MyPhotoList;
import com.lesgirls.utils.ScreenUtils;
import com.lesgirls.views.DividerItemDecoration;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MyPhotoFragment extends Fragment {

    private OnMyPhotoListener listener;
    private RecyclerView rvMyPhoto;
    private RelativeLayout rlUploadPhoto;
    private MyPhotoAdapter adapter;
    private boolean isDeleted = false;

    public MyPhotoFragment() {
    }
    public static MyPhotoFragment newInstance() {
        MyPhotoFragment fragment = new MyPhotoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_photo, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        rvMyPhoto = (RecyclerView) view.findViewById(R.id.rvMyPhoto);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvMyPhoto.setLayoutManager(layoutManager);
        rvMyPhoto.addItemDecoration(new DividerItemDecoration(getActivity()));

        rlUploadPhoto = (RelativeLayout) view.findViewById(R.id.rlBottomPanel);
        rlUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onUpload();
                }
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMyPhotoListener) {
            listener = (OnMyPhotoListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setData(MyPhotoList list) {
        adapter = new MyPhotoAdapter(getActivity(), list, rvMyPhoto.getWidth()/2);
        rvMyPhoto.setAdapter(adapter);
    }

    public interface OnMyPhotoListener {
        void onUpload();
    }
    public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.ViewHolder>{
        private final int cellWidth;
        private MyPhotoList list;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView ivPhoto;
            public CardView cv;
            public long id;
            public ViewHolder(View view) {
                super(view);
                ivPhoto = (ImageView) view.findViewById(R.id.ivRandom);
                cv = (CardView) view.findViewById(R.id.card_view);
            }
        }
        public MyPhotoAdapter(Context context, MyPhotoList list, int cellWidth) {
            this.list = list;
            Long ll = new Long(Math.round(ScreenUtils.getScreenWidth(getActivity())/2.5));
            this.cellWidth = ll.intValue();//cellWidth;
        }
        @Override
        public MyPhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            Attachment att = this.list.get(position);
            final long l = att.getID();
            if(l!=0) {
                String token = ((SettingsActivity) getActivity()).getSettings().getToken();
                String url = LesGirls.base_url + "downloadImage/?token=" + token + "&Attachment[id]=" + String.valueOf(l);
                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.ivPhoto.setImageBitmap(bitmap);
                        ViewGroup.LayoutParams params = holder.cv.getLayoutParams();
                        Drawable dr = holder.ivPhoto.getDrawable();
                        Rect r = dr.getBounds();
                        params.height = 200;//r.height();
                        holder.ivPhoto.setLayoutParams(params);
                        holder.cv.setLayoutParams(params);
                    }
                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {}
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {}
                };
                Picasso.with(getActivity()).load(url).into(holder.ivPhoto);
                holder.ivPhoto.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Bundle b = new Bundle();
                        b.putLong("id", l);
                        SettingsActivity act = (SettingsActivity) getActivity();
                        if(!isDeleted){isDeleted = true;getActivity().getLoaderManager().initLoader(R.id.myphotodelete_loader,b,act);}
                        else getActivity().getLoaderManager().restartLoader(R.id.myphotodelete_loader,b,act);
                        return true;
                    }
                });
            }
            else holder.ivPhoto.setImageResource(R.drawable.ic_default_photo);
        }

        public int getItemCount() {
            return ((list == null)?0:list.size());
        }
    }
}
