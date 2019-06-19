package com.example.myapplication;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationHelper {

    public static void displayNotification(Context context, String title, String body) {
        Intent intent = new Intent(context, profileact2.class);
        PendingIntent pendngIntent = PendingIntent.getActivity(
          context,
          100,
          intent,
          PendingIntent.FLAG_CANCEL_CURRENT
        );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,profileact2.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_633584)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setContentIntent(pendngIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat mnotificationMgr = NotificationManagerCompat.from(context);
        mnotificationMgr.notify(1, mBuilder.build());
    }
}