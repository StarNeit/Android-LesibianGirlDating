package com.lesgirls.fragments.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lesgirls.ActActivity;
import com.lesgirls.LesGirls;
import com.lesgirls.R;
import com.lesgirls.network.model.entity.DataActivity;
import com.lesgirls.network.model.entity.ListDataActivity;
import com.squareup.picasso.Picasso;

public class FollowingFragment extends Fragment {

    private static final String TAG = "FollowingFragment";
    ActActivity act;

    private OnFollowind listener;
    private long userID;
    private RecyclerView rvData;
    private ListDataActivity data;
    private FollowingAdapter adapter;

    public FollowingFragment() {
    }

    public static FollowingFragment newInstance(long id) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getLong("id");
        }
        act = (ActActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle saved) {
        super.onViewCreated(view, saved);
        data = new ListDataActivity();
        rvData = (RecyclerView) view.findViewById(R.id.rvFollow);
        rvData.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rvData.setLayoutManager(lm);
        adapter = new FollowingAdapter(data);
        rvData.setAdapter(adapter);

        int i = 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowind) {
            listener = (OnFollowind) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFollowind {
        void onCard();
    }

    public void setData(ListDataActivity aData) {
        int i = 0;
        for (DataActivity d : aData) {
            this.data.add(d);
            i++;
        }
        Log.i(TAG, "Data added " + i + " records");
        adapter.notifyDataSetChanged();
    }

    public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {
        private static final String TAG = "FollowingAdapter";
        private final ListDataActivity list;
        private ActActivity act;
        public FollowingAdapter(ListDataActivity arr){
            super();
            act = (ActActivity) getActivity();
            this.list = arr;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final DataActivity dataActivity = list.get(position);
            /*
            String url = LesGirls.base_url + "downloadImage/?token="+act.getSettings().getToken()
            Picasso.with(act).load()
            */
            holder.tvNameFrom.setText(dataActivity.uf.getFullName()+"("+dataActivity.uf.getID()+")");
            String sAct;
            switch (dataActivity.activity.getActivityID()){
                case 0:{sAct="watched";break;}
                case 1:{sAct="followed";break;}
                case 2:{sAct="liked";break;}
                case 3:{sAct="blocked";break;}
                default:sAct="liked";
            }
            holder.tvActivity.setText(sAct);
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView icon;
            public TextView tvNameFrom;
            public TextView tvActivity;
            public TextView tvNameTo;
            public TextView tvWho;
            public TextView tvTime;
            public ViewHolder(View itemView) {
                super(itemView);
                icon = (ImageView) itemView.findViewById(R.id.ivIcon);
                tvNameFrom = (TextView) itemView.findViewById(R.id.tvNameFrom);
                tvActivity = (TextView) itemView.findViewById(R.id.tvActivity);
                tvNameTo = (TextView) itemView.findViewById(R.id.tvNameTo);
                tvWho = (TextView) itemView.findViewById(R.id.tvWho);
                tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            }
        }
    }
}