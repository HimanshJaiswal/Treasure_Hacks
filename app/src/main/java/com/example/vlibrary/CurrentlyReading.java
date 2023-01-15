package com.example.vlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class CurrentlyReading extends AppCompatActivity {
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currently_reading);
        drawerLayout=findViewById(R.id.drawer_layout);

    }
    public void ClickMenu(View view)
    {
        Homepage.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view)
    {
        Homepage.closeDrawer(drawerLayout);
    }
    /*public void ClickCurrentlyReading(View view)
    {
        Homepage.redirectActivity(this,CurrentlyReading.class);
    }
    public void ClickAboutUs(View view)
    {
        Homepage.redirectActivity(this,AboutUs.class);
    }
    public void ClickLogout(View view)
    {
        Homepage.redirectActivity(this);
    }
    */

}