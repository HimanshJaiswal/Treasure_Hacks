package com.VIT.vlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Colleges_List extends AppCompatActivity implements collegeListAdapter.ItemClickListener{

    FirebaseAuth fAuth;
    DatabaseReference database;
    RecyclerView collegeNames;
    collegeListAdapter collegeNamesAdapter;
    ArrayList<College> collegeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colleges);

        fAuth = FirebaseAuth.getInstance();

        collegeNames = findViewById(R.id.collegeNames);
        collegeNames.setHasFixedSize(true);
        collegeList = new ArrayList<College>();
        collegeNames.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        collegeNamesAdapter = new collegeListAdapter(this, collegeList, this);
        collegeNames.setAdapter(collegeNamesAdapter);

        database = FirebaseDatabase.getInstance(" https://vlibrary-fc171-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Colleges");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                currentlyReadingList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
                    College coll = snapshot.getValue(College.class);
                    collegeList.add(coll);

                }

                collegeNamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View view, int position) {

        College college = collegeListAdapter.list.get(position);
        Intent i = new Intent(getApplicationContext(),AboutUs.class);
//        Log.d(TAG, "URL: " + book.getUrl());
//            i.putExtra("URL", book.getUrl());
//            i.putExtra("pdfName", book.getTitle());
        startActivity(i);
    }
}