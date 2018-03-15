package com.technovate18.sanskar.touristy.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonParseException;
import com.technovate18.sanskar.touristy.activity.MainActivity;
import com.technovate18.sanskar.touristy.database.DBhandler;
import com.technovate18.sanskar.touristy.models.FeedPostModel;
import com.technovate18.sanskar.touristy.utils.Config;
import com.technovate18.sanskar.touristy.utils.Constants;
import com.technovate18.sanskar.touristy.utils.NotificationUtils;
import com.technovate18.sanskar.touristy.utils.Utils;

import java.util.Map;

/**
 * Created by hadoop on 15/3/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{


    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            try {

                Map<String,String> map = remoteMessage.getData();
                Utils.saveData(Constants.PREF_NOTIFICATION_JSON, map.toString());


                handleDataMessage(map);


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }else{
            // If the app is in background, firebase itself handles the notification
        }
    }

    private void handleDataMessage(Map map) {
        Log.e(TAG, "push map: " + map.toString());

        try {
            Map data = map; //json.getJSONObject("data");

            String title = data.get("title").toString();
            String description = data.get("description").toString();
            String author = data.get("author").toString();
            // String imageUrl = data.getString("image");      // absent
            String timestamp = data.get("timestamp").toString();
            String noticenumber = data.get("noticenumber").toString();
            // JSONObject payload = data.getJSONObject("payload");     //absent

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "description: " + description);
            Log.e(TAG, "author: " + author);
            Log.e(TAG, "timestamp: " + timestamp);
            Log.e(TAG, "noticenumber: " + noticenumber);
            //Log.e(TAG, "imageUrl: " + imageUrl);

            DBhandler dbh = new DBhandler(getApplicationContext());
            dbh.addData(new FeedPostModel(timestamp,title,description,author,noticenumber));

//            String str = map.toString() + ConstantNames.JSON_SEPARATOR +Utils.getData(ConstantNames.PREF_FEED_JSON,"");
//            Utils.saveData(ConstantNames.PREF_FEED_JSON,str);

//            Log.d("PREF_FEED_JSON",str);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", description);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                resultIntent.putExtra("message", description);
                resultIntent.setAction(Constants.ACTION_OPEN_NOTIF_FRAGMENT);

                // check for image attachment
                if (true/*TextUtils.isEmpty(imageUrl)*/) {
                    showNotificationMessage(getApplicationContext(), title, description, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    // showNotificationMessageWithBigImage(getApplicationContext(), title, description, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JsonParseException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }



}
