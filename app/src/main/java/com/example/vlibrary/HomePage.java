package com.VIT.vlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements myAdapter2.ItemClickListener{

    private long pressedTime;
    FirebaseAuth fAuth;
    DatabaseReference database;
    ArrayList<Books> currentlyReadingList;
    ArrayList<Books> mostReadList;
    ArrayList<Books> newBooksList;
    RecyclerView currentlyReading;
    RecyclerView mostRead;
    RecyclerView newBooks;
    myAdapter2 myReadingsAdapter;
    myAdapter2 mostReadAdapter;
    myAdapter2 newBooksAdapter;
    Button currReading;
    Button assignment;

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }


    ImageButton setting;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        assignment = findViewById(R.id.assignment);
        currentlyReading = findViewById(R.id.myReadingsList);
        currentlyReading.setHasFixedSize(true);
//        currentlyReading.setLayoutManager(new LinearLayoutManager(this));
        currentlyReadingList = new ArrayList<Books>();
        currentlyReading.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        myReadingsAdapter = new myAdapter2(this, currentlyReadingList,this);
        currentlyReading.setAdapter(myReadingsAdapter);


        mostRead = findViewById(R.id.mostReadList);
        mostRead.setHasFixedSize(true);
//        mostRead.setLayoutManager(new LinearLayoutManager(this));
        mostRead.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mostReadList = new ArrayList<Books>();
        mostReadAdapter = new myAdapter2(this, mostReadList,this);
        mostRead.setAdapter(mostReadAdapter);


        newBooks = findViewById(R.id.newBooksList);
        newBooks.setHasFixedSize(true);
        newBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        newBooksList = new ArrayList<Books>();
        newBooksAdapter = new myAdapter2(this, newBooksList,this);
        newBooks.setAdapter(newBooksAdapter);

//        Log.d(TAG, "onCreate: " + "most read");
        currReading = findViewById(R.id.myReadings);
        fAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        setting = findViewById(R.id.button5);

        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }


        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, Colleges_List.class));
            }
        });
        currReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,CurrentlyReading.class));
            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Settings.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance(" https://vlibrary-fc171-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Finance");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                currentlyReadingList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
                    Books book = snapshot.getValue(Books.class);
                    currentlyReadingList.add(book);

                }

                myReadingsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database = FirebaseDatabase.getInstance(" https://vlibrary-fc171-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Computer Science");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                currentlyReadingList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
                    Books book = snapshot.getValue(Books.class);
                    mostReadList.add(book);

                }

                mostReadAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        database = FirebaseDatabase.getInstance(" https://vlibrary-fc171-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Mechanical Engineering");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                currentlyReadingList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
                    Books book = snapshot.getValue(Books.class);
                    newBooksList.add(book);

                }

                newBooksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
        public void ClickMenu (View view)
        {
            openDrawer(drawerLayout);
        }

        static void openDrawer (DrawerLayout drawerLayout)
        {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        public void ClickLogo (View view)
        {
            closeDrawer(drawerLayout);
        }

        public static void closeDrawer (DrawerLayout drawerLayout)
        {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
        public void ClickCurrent (View view)
        {
            Intent intent2 = new Intent(view.getContext(), CurrentlyReading.class);
            startActivity(intent2);
        }
        public void ClickCategory (View view)
        {
            Intent intent2 = new Intent(view.getContext(), Category.class);
            startActivity(intent2);
        }
        public void ClickAboutUs (View view)
        {
            Intent intent2 = new Intent(view.getContext(), AboutUs.class);
            startActivity(intent2);
        }
        public void ClickEditInfo (View view)
        {
            Intent intent2 = new Intent(view.getContext(), PersonalInfo.class);
            startActivity(intent2);
        }

        public void ClickNewBooks (View view)
        {
            Intent intent2 = new Intent(view.getContext(), newBooks.class);
            startActivity(intent2);
        }
        public void ClickLogout (View view)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent2 = new Intent(HomePage.this, MainActivity.class);
            this.finish();
            startActivity(intent2);
        }


        public void logout (Activity activity)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Logout");
            builder.setMessage("are you sure,you want to logout ?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                activity.finishAffinity();
//                System.exit(0);
//                logout();
                    //Intent i = new Intent(Homepage.this,MainActivity.class);
                    //startActivity(i);
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            builder.show();
        }

        @Override
        protected void onPause ()
        {
            super.onPause();
            HomePage.closeDrawer(drawerLayout);

        }

    @Override
    public void onClick(View view, int position)
    {
        Books book = myReadingsAdapter.list.get(position);
        Intent i = new Intent(getApplicationContext(),PDFViewer.class);
//        Log.d(TAG, "URL: " + book.getUrl());
        i.putExtra("URL", book.getUrl());
        i.putExtra("pdfName", book.getTitle());
        startActivity(i);
    }




}
