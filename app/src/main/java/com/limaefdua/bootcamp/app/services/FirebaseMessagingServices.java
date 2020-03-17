package com.limaefdua.bootcamp.app.services;
//
// Created by maftuhin on 10/24/2019.
//

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.limaefdua.bootcamp.R;
import com.limaefdua.bootcamp.auth.LoginActivity;

import java.util.Map;

public class FirebaseMessagingServices extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage.getData(), remoteMessage.getNotification());
    }

    private void sendNotification(Map<String, String> data, RemoteMessage.Notification notification) {
        int requestId = (int) System.currentTimeMillis();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "fcm")
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setLights(Color.BLUE, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, mBuilder.build());
    }
}
