package com.example.componentesandroiddemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyForegroundService extends Service {
    private static final String CHANNEL_ID = "demo_fgs_channel";



    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Demo Foreground Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Servicio activo")
                .setContentText("Demo de Foreground Service ejecutándose...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .build();
        Toast.makeText(this, "Foreground Service corriendo", Toast.LENGTH_SHORT).show();
        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
