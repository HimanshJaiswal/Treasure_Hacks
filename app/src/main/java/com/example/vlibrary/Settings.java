package com.VIT.vlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;

//import android.app.NotificationChannel;
//import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Button notifyMe,shareApp,rateApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notifyMe = findViewById(R.id.notify);
        shareApp = findViewById(R.id.share);
        rateApp = findViewById(R.id.rate);


        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),shareApp.class);
                startActivity(i);
            }
        });

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel =new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT );
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder=new NotificationCompat.Builder(Settings.this,"My Notification");
                builder.setContentTitle("STOP WASTING YOUR TIME!!!");
                builder.setContentText("You have left a book incomplete");
                builder.setSmallIcon(R.drawable.vitlogo);
                builder.setAllowSystemGeneratedContextualActions(true);

                NotificationManagerCompat managerCompat =NotificationManagerCompat.from(Settings.this);
                managerCompat.notify(1,builder.build());

            }
        });

        rateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),rateUs.class);
                startActivity(i);
            }
        });
    }
}