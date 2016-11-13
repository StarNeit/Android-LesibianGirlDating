package com.lesgirls.fragments.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesgirls.R;

public class LoginFragment extends Fragment {

    private OnLoginListener listener;
    private Button bFBLogin;
    private RelativeLayout rlLogin;
    private AutoCompleteTextView email;
    private EditText password;
    private TextView tvSignup;
    private TextView tvForgot;

    public LoginFragment() {
    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        bFBLogin = (Button) view.findViewById(R.id.bFBLogin);
        bFBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onFBLogin();
                }
            }
        });
        rlLogin = (RelativeLayout) view.findViewById(R.id.rlLogin);
        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onLogin(email.getText().toString(), password.getText().toString());
                }
            }
        });
        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        //email.setText("dvd@gmail.com");
        password = (EditText) view.findViewById(R.id.password);
        //password.setText("Vargas");
        tvSignup = (TextView) view.findViewById(R.id.tvSignUp);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSignup();
            }
        });
        tvForgot = (TextView) view.findViewById(R.id.tvForgot);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForgot();
            }
        });
    }
        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginListener) {
            listener = (OnLoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public void setValues(String emailValue, String passwordValue){
        email.setText(emailValue);
        password.setText(passwordValue);

    }
    public interface OnLoginListener {
        void onLogin(String email, String password);
        void onFBLogin();
        void onSignup();
        void onForgot();
    }
}
