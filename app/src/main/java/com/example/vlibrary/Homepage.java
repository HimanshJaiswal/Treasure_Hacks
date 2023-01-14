package com.example.vlibrary;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import static com.example.vlibrary.Category.redirectActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    ImageButton setting;
    DrawerLayout drawerLayout;

    //private static final int PERMISSION_REQUEST_CAMERA = 69;
    //private AppBarComfiguration mAppBarConfiguration;
    //private Toolbar toolbar;
    //ActionBarDrawerToggle mDrawerToogle;



    //public static void redirectActivity(CurrentlyReading currentlyReading) {
    //}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        drawerLayout=findViewById(R.id.drawer_layout);
        setting = findViewById(R.id.button5);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,Settings.class);
                startActivity(intent);
            }
        });



    }
    /*public void Clickbutton5(View view)
    {
       //Intent intent = new Intent(view.getContext(),Settings.class);
        //startActivity(intent);
        redirectActivity(this,Settings.class);
    }*/
    public void ClickMenu(View view)
    {

        openDrawer(drawerLayout);

    }

    static void openDrawer(DrawerLayout drawerLayout)
    {
        drawerLayout.openDrawer(GravityCompat.START);

    }
    public void ClickLogo(View view)
    {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickCurrent(View view)
    {
        Intent intent2 = new Intent(view.getContext(),CurrentlyReading.class);
        startActivity(intent2);
        //redirectActivity(this,CurrentlyReading.class);

    }
    public void ClickCategory(View view)
    {
        Intent intent2 = new Intent(view.getContext(),Category.class);
        startActivity(intent2);
        //redirectActivity(this,Category.class);

    }
    public void ClickAboutUs(View view)
    {
        Intent intent2 = new Intent(view.getContext(),AboutUs.class);
        startActivity(intent2);
        //redirectActivity(this,AboutUs.class);

    }
    public void ClickEditInfo(View view)
    {
        Intent intent2 = new Intent(view.getContext(), EditText.class);
        startActivity(intent2);
        //redirectActivity(this,EditInfo.class);

    }
    public void ClickLogout(View view)
    {
        FirebaseAuth.getInstance().signOut();

        Intent intent2 = new Intent(Homepage.this,MainActivity.class);
        this.finish();
        startActivity(intent2);

    }

    public static void logout(Activity activity)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("are you sure,you want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                activity.finishAffinity();
                System.exit(0);
                //Intent i = new Intent(Homepage.this,MainActivity.class);
                //startActivity(i);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();

            }
        });
        builder.show();
    }


    //public static void redirectActivity(Activity activity,Class aClass) {
        //Intent intent = new Intent(activity,aClass);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //activity.startActivity(intent);
    //}

    @Override
    protected void onPause() {
        super.onPause();
        Homepage.closeDrawer(drawerLayout);

    }
}