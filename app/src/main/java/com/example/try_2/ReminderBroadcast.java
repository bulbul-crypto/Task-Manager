
// This file handles the function of setting notifications and reminder of a particular task that has been sorted by task scheduler

// Manifest.xml file also contains some infos about this file


package com.example.try_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyTask").
        setSmallIcon(R.drawable.ic_notifications).
                setContentTitle(Constant.staticTask.getTaskName()).
                setContentText(Constant.staticTask.getTaskName()+" starts at "+Integer.toString(Constant.staticTask.getStartTime())).
                setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200,builder.build());
    }
}
