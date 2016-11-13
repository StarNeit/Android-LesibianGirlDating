package com.lesgirls.fragments.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lesgirls.R;

public class SplashFragment extends Fragment {
    private OnSplashActionsListener listener;
    private RelativeLayout rlLogin;
    private RelativeLayout rlGetStarted;

    public SplashFragment() {
    }
    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        rlLogin = (RelativeLayout) view.findViewById(R.id.rlLogin);
        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin();
            }
        });
        rlGetStarted = (RelativeLayout) view.findViewById(R.id.rlGetStarted);
        rlGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRegistration();
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSplashActionsListener) {
            listener = (OnSplashActionsListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public interface OnSplashActionsListener{
        void onLogin();
        void onRegistration();
    }
}
