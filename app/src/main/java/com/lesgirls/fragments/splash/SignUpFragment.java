package com.lesgirls.fragments.splash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesgirls.R;
import com.lesgirls.StartActivity;
import com.lesgirls.utils.FileOperator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpFragment extends Fragment {
    public static final int REQUEST_TAKE_PHOTO = 1;
    private String currentPhotoPath;
    private OnSignUpListener listener;
    private Button bFBSignUp;
    private RelativeLayout rlSignUp;
    private AutoCompleteTextView email;
    private EditText password;
    private EditText confirm;
    private TextView tvLogin;
    private ImageView ivBack;
    private ImageView ivCamera;
    private ImageView ivPhoto;
    private boolean isPhoto = false;

    public SignUpFragment() {
    }
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        bFBSignUp = (Button) view.findViewById(R.id.bFBSignup);
        bFBSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onFBSignUp();
                }
            }
        });
        rlSignUp = (RelativeLayout) view.findViewById(R.id.rlSignup);
        rlSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    String pass = password.getText().toString();
                    String conf = confirm.getText().toString();
                    if(conf.equals(pass) && isPhoto) {
                        listener.onSignUp(email.getText().toString(), password.getText().toString());
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                                .setTitle("Error")
                                .setMessage("Confirm password fail")
                                .setCancelable(true)
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        builder.create().show();
                    }
                }
            }
        });
        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        //email.setText("dvd@gmail.com");
        password = (EditText) view.findViewById(R.id.password);
        //password.setText("duster");
        confirm = (EditText) view.findViewById(R.id.confirm);
        //confirm.setText("duster");
        tvLogin = (TextView) view.findViewById(R.id.tvLog_in);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin(true);
            }
        });
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBack(true);
            }
        });
        ivCamera = (ImageView) view.findViewById(R.id.ivCamera);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StartActivity) getActivity()).takeSelphie();
            }
        });
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignUpListener) {
            listener = (OnSignUpListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setBitmap(Bitmap bitmap){
        //int targetW = ivPhoto.getMaxWidth();
        //int targetH = ivPhoto.getMaxHeight();
        //Bitmap scaled = Bitmap.createScaledBitmap(bitmap, targetW, targetH, false);
        ivPhoto.setImageBitmap(bitmap);
        ivCamera.setVisibility(View.INVISIBLE);
        //galleryAddPic();
    }
    public void setBitmap(Uri uri){
       String sFile = FileOperator.getRealPathFromURI(getActivity(), uri);
        Bitmap bitmap = BitmapFactory.decodeFile(sFile);
        setBitmap(bitmap);
        galleryAddPic(uri);
        if(!sFile.isEmpty()) isPhoto = true;
    }
    private void galleryAddPic(Uri uri) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(uri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    public interface OnSignUpListener {
        void onSignUp(String email, String password);
        void onFBSignUp();
        void onLogin(boolean checked);
        void onBack(boolean checked);
    }
}
