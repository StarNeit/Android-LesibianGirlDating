package com.lesgirls.fragments.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lesgirls.R;

public class MainSettingsFragment extends Fragment {

    private OnMainSettingsListener listener;
    private RelativeLayout rlProfile;
    private RelativeLayout rlMyPhoto;
    private RelativeLayout rlLogout;
    private RelativeLayout rlMatch;

    public MainSettingsFragment() {
        // Required empty public constructor
    }
    public static MainSettingsFragment newInstance() {
        MainSettingsFragment fragment = new MainSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_settings, container, false);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainSettingsListener) {
            listener = (OnMainSettingsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        rlProfile = (RelativeLayout) view.findViewById(R.id.rlProfile);
        rlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) listener.onProfile();
            }
        });
        rlMyPhoto = (RelativeLayout) view.findViewById(R.id.rlMyPhoto);
        rlMyPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onMyPhoto();
                }
            }
        });
        rlMatch = (RelativeLayout) view.findViewById(R.id.rlMatch);
        rlMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onMatch();
                }
            }
        });
        rlLogout = (RelativeLayout) view.findViewById(R.id.rlLogOut);
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onLogout();
                }
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public interface OnMainSettingsListener {
        void onProfile();
        void onMyPhoto();
        void onChangeMyProfilePicture();
        void onTellAFriends();
        void onMatch();
        void onVideo();
        void onAccount();
        void onLogout();
    }
}
