package com.codepath.socialshopper.socialshopper.Listeners;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.codepath.socialshopper.socialshopper.R;
import com.codepath.socialshopper.socialshopper.Activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


/**
 * Created by saripirala on 10/11/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String YES_ACTION = "YES_ACTION";
    private static final String NO_ACTION = "NO_ACTION";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String from = remoteMessage.getFrom();
        Map<String,String> data = remoteMessage.getData();
        Log.i("Push received", data.toString());
        String messageContent = data.get("payload");
        displayNotification(messageContent);
    }

    private void displayNotification(String message){

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent yesIntent = getNotificationIntent();
        yesIntent.setAction(YES_ACTION);


        Intent noIntent = getNotificationIntent();
        noIntent.setAction(NO_ACTION);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("New Pick Up !")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(PendingIntent.getActivity(this, 0, getNotificationIntent(), PendingIntent.FLAG_UPDATE_CURRENT))
                //TODO Figure out why the action icons aren't showing up
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_accept,
                        "Yes",
                        PendingIntent.getActivity(this, 1, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_deny,
                        "No",
                        PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());

    }


    private Intent getNotificationIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

}