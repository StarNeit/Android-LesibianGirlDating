package com.lesgirls.fragments.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesgirls.R;
import com.lesgirls.SettingsActivity;
import com.lesgirls.utils.DateOperator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProfileFragment extends Fragment {

    private OnProfile listener;

    private String[] looking;

    private RelativeLayout rlProfileName;
    private TextView tvProfileName;
    private RelativeLayout rlAge;
    private TextView tvAge;
    private RelativeLayout rlIAmA;
    private TextView tvIAmA;
    private RelativeLayout rlLookingFor;
    private TextView tvLookingFor;
    private RelativeLayout rlPassword;
    private TextView tvPassword;
    private SettingsActivity act;

    private String fullName;
    private int sexType;
    private int look;
    private Date birth;
    private boolean updated = false;

    public ProfileFragment() {
    }
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        looking = getActivity().getResources().getStringArray(R.array.looking);
        updated = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        rlProfileName = (RelativeLayout) view.findViewById(R.id.rlProfileName);
        rlProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) listener.onProfileName(tvProfileName.getText().toString());
            }
        });
        tvProfileName = (TextView) view.findViewById(R.id.tvProfileName);
        rlAge = (RelativeLayout) view.findViewById(R.id.rlAge);
        rlAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = Calendar.getInstance().getTime();
                if(listener != null) listener.onAge(d);
            }
        });
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        rlIAmA = (RelativeLayout) view.findViewById(R.id.rlIAmA);
        rlIAmA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tvIAmA.getText().toString();
                String sType = "";
                int iType = 0;
                String[] arr = s.split(" ");
                if(arr.length>3) sType=arr[3];
                if(sType.equals("Lesbian")) iType = 0;
                else if(sType.equals("Bisexual")) iType = 1;
                else if(sType.equals("Pansexual")) iType = 2;
                listener.onIamA(iType);
            }
        });
        tvIAmA = (TextView) view.findViewById(R.id.tvIAmA);
        rlLookingFor = (RelativeLayout) view.findViewById(R.id.rlLookingFor);
        rlLookingFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    String s = tvLookingFor.getText().toString();
                    String[] ss = s.split(" ");
                    String sLooking = looking[0];
                    int iLocking = 0;
                    if (ss.length > 2) {
                        sLooking = ss[2];
                        if (sLooking.equals(looking[0])) iLocking = 0;
                        else if (sLooking.equals(looking[1])) iLocking = 1;
                        else if (sLooking.equals(looking[2])) iLocking = 2;
                        else if (sLooking.equals(looking[3])) iLocking = 3;
                    }
                    listener.onLoockingFor(iLocking);
                }
            }
        });
        tvLookingFor = (TextView) view.findViewById(R.id.tvLookingFor);
        rlPassword = (RelativeLayout) view.findViewById(R.id.rlPassword);
        rlPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) listener.onPassword();
            }
        });
        tvPassword = (TextView) view.findViewById(R.id.tvPassword);

        Bundle b = new Bundle();
        act = (SettingsActivity) getActivity();
        b.putLong("id", act.getSettings().getUserID());
        act.getLoaderManager().initLoader(R.id.profile_loader,b, act);
    }
        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProfile) {
            listener = (OnProfile) context;
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
    public void setProfileName (String profileName){
        tvProfileName.setText(profileName);
        this.fullName = profileName;
    }
    public void setAge(int age){
        String s = tvAge.getText().toString()+" : "+String.valueOf(age);
        tvAge.setText(s);
    }
    public void setAge(Date birth){
        Date current = Calendar.getInstance().getTime();
        int age = current.getYear()-birth.getYear();
        setAge(age);
        this.birth = birth;
    }
    public void setSexualType(int iType){
        String[] aType = getActivity().getResources().getStringArray(R.array.sexual_type);
        String[] ss = tvIAmA.getText().toString().split(" ");
        tvIAmA.setText(ss[0]+" "+ss[1]+" "+ss[2]+" "+aType[iType]);
        sexType = iType;
    }
    public void setLooking(int iLooking){
        String[] ss = tvLookingFor.getText().toString().split(" ");
        tvLookingFor.setText(ss[0]+" "+ss[1]+" "+looking[iLooking]);
        look = iLooking;
    }
    public void setPassword (String password){
        //tvPassword.setText(password);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.password)
                .setMessage(R.string.password_changed)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public String getFullName() {
        return fullName;
    }

    public int getSexualType() {
        return sexType;
    }

    public int getLooking() {
        return look;
    }

    public Date getBirthDate() {
        return birth;
    }

    public interface OnProfile{
        void onProfileName(String profileName);
        void onAge(Date date);
        void onIamA(int type);
        void onLoockingFor(int lookingFor);
        void onPassword();
        void onViewMyProfile();
        void onShareMyProfile();
        void onDescription();
    }
}
