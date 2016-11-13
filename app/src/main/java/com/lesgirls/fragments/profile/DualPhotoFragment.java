package com.lesgirls.fragments.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lesgirls.R;
import com.squareup.picasso.Picasso;

public class DualPhotoFragment extends Fragment {

    private OnDualPhotoFragment listener;
    private ImageView ivFirst;
    private ImageView ivSecond;

    public DualPhotoFragment() {
    }
    public static DualPhotoFragment newInstance() {
        DualPhotoFragment fragment = new DualPhotoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dual_photo, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        ivFirst = (ImageView) view.findViewById(R.id.ivFirst);
        ivSecond = (ImageView) view.findViewById(R.id.ivSecond);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDualPhotoFragment) {
            listener = (OnDualPhotoFragment) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setURL(String url1, String url2) {
        Picasso.with(getActivity()).load(url1).into(ivFirst);
        Picasso.with(getActivity()).load(url2).into(ivSecond);
    }

    public interface OnDualPhotoFragment {
        void onDualPhoto();
        void onDualBack();
    }
}
