package com.lesgirls.fragments.splash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesgirls.R;
public class ForgotFragment extends Fragment {

    private OnForgotListener listener;
    private EditText edEmail;
    private ImageView ivBack;
    private RelativeLayout rlSend;


    public ForgotFragment() {
    }
    public static ForgotFragment newInstance() {
        ForgotFragment fragment = new ForgotFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        edEmail = (EditText) view.findViewById(R.id.email);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBack(0);
            }
        });
        rlSend = (RelativeLayout) view.findViewById(R.id.rlSend);
        rlSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSend(edEmail.getText().toString());
            }
        });

    }
        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnForgotListener) {
            listener = (OnForgotListener) context;
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

    public interface OnForgotListener {
        void onSend(String email);
        void onBack(int i);
    }
}
