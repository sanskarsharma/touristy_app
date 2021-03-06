package com.technovate18.sanskar.touristy.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.technovate18.sanskar.touristy.R;
import com.technovate18.sanskar.touristy.adapters.FeedAdapter;
import com.technovate18.sanskar.touristy.database.DBhandler;
import com.technovate18.sanskar.touristy.models.FeedPostModel;
import com.technovate18.sanskar.touristy.utils.Config;
import com.technovate18.sanskar.touristy.utils.NotificationUtils;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NotificationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // for firebase FCM code
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        // firebase FCM code below, for Notifications fragment
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    Toast.makeText(getActivity().getApplicationContext(), "FCM Token received: " , Toast.LENGTH_LONG).show();

                    //displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getActivity().getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    refreshFeed();
                    // txtMessage.setText(message);
                }
            }
        };


    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();

        // register FCM registration complete receiver
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view);
        registerForContextMenu(recyclerView); /// registering for context menu which gives edit and delete options

        refreshFeed();

    }


    private void refreshFeed(){

        List<FeedPostModel> items = null;

//        items = ...    take list of FeedPostModel here after parsing json and combining with older json from prefs
//        String feedstr = Utils.getData(ConstantNames.PREF_FEED_JSON,"");
//        if(!feedstr.equals(""))
//            items = MapParsing.feedParser(feedstr);

        DBhandler dbh = new DBhandler(getActivity().getApplicationContext());
        items = dbh.getAllEntries();


        if(!items.isEmpty()){

            Log.e("from refreshfeed()"," items is NOT empty");
            Log.e("from refreshfeed()"," items : \n "+ items.toString());

//            if(txtRegId.getVisibility()==View.VISIBLE){
//                txtRegId.setText("");
//                txtRegId.setVisibility(View.GONE);
//            }

            Collections.reverse(items);             // reversing list so that new entries show on top

            FeedAdapter adapter = new FeedAdapter(items);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else {
//            txtRegId.setVisibility(View.VISIBLE);
//            txtRegId.setText(" 0 new Notices !"+"\n"+" Happy times ;)");
        }

    }



}
