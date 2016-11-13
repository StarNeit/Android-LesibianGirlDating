package com.lesgirls.fragments.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lesgirls.LesGirls;
import com.lesgirls.MainActivity;
import com.lesgirls.ProfileActivity;
import com.lesgirls.R;
import com.lesgirls.network.model.entity.AppUser;
import com.squareup.picasso.Picasso;

public class MainProfileFragment extends Fragment {
    private AppUser user;
    private OnMainProfileListener listener;
    private long att_id;
    private String token;
    private ImageView ivPhoto;
    private ImageView ivRight;
    private CheckBox ivFollow;
    private CheckBox cbLike;
    private RelativeLayout rlMessage;
    private String message = "The text message";

    public MainProfileFragment() {
    }
    public static MainProfileFragment newInstance() {
        MainProfileFragment fragment = new MainProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = ((ProfileActivity)getActivity()).getUser();
        att_id = user.getAttachment().getID();
        token = ((ProfileActivity) getActivity()).getSettings().getToken();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_profile, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        String url = LesGirls.base_url + "downloadImage/?token=" + token + "&Attachment[id]=" + String.valueOf(att_id);
        ivRight = (ImageView) view.findViewById(R.id.ivRight);
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) listener.onRightClick();
            }
        });
        ivFollow = (CheckBox) view.findViewById(R.id.ivFollow);
        ivFollow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onFollow(isChecked, user.getUser().getID());
            }
        });
        cbLike = (CheckBox) view.findViewById(R.id.cbLike);
        cbLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onLike(isChecked, user.getUser().getID());
            }
        });
        rlMessage = (RelativeLayout) view.findViewById(R.id.rlMessage);
        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddMessage(user.getUser().getID(),message);
            }
        });
        Picasso.with(getActivity()).load(url).into(ivPhoto);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainProfileListener) {
            listener = (OnMainProfileListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnMainProfileListener {
        void onBack();
        void onRightClick();
        void onFollow(boolean set, long toUser);
        void onLike(boolean set, long toUser);
        void onAddMessage(long userUD, String message);
    }
}
