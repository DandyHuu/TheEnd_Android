package com.t3h.appdc;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ServiceActivity extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Token");
        reference.child("HeoTeam").setValue(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String CHANEL_ID = "CHANEL_ID";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            CharSequence name;
            NotificationChannel channel = new NotificationChannel(CHANEL_ID, CHANEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentText(remoteMessage.getNotification().getBody());
        builder.setContentTitle(remoteMessage.getNotification().getTitle());
        manager.notify(123223423, builder.build());
    }
}
