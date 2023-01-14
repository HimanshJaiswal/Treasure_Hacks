package com.example.vlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vlibrary.R;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Button b1=(Button)findViewById(R.id.button2);
        Button b2=(Button)findViewById(R.id.button3);
        Button b3=(Button)findViewById(R.id.button);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshareapp();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openrateus();
            }
        });




        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel =new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT );
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder=new NotificationCompat.Builder(Settings.this,"My Notification");
                builder.setContentTitle("My Title");
                builder.setContentText("Hello from V-Library,this is a simple notification");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAllowSystemGeneratedContextualActions(true);

                NotificationManagerCompat managerCompat =NotificationManagerCompat.from(Settings.this);
                managerCompat.notify(1,builder.build());

            }
        });
    }

    public void openshareapp()
    {
        Intent i=new Intent(this,share_app.class);
        startActivity(i);
    }
    public void openrateus()
    {
        Intent i=new Intent(this,rateUs.class);
        startActivity(i);
    }
}