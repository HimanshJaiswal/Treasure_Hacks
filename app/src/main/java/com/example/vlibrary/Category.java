package com.VIT.vlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends AppCompatActivity implements myAdapter2.ItemClickListener{


    public Button button;
    DrawerLayout drawerLayout;
    DatabaseReference database;
    ArrayList<Books> computerBooksList;
    RecyclerView computerScienceBooks;
    myAdapter2 computerAdapter;
    ArrayList<Books> MechanicalBooksList;
    RecyclerView MechanicalBooks;
    myAdapter2 MechanicalAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        computerScienceBooks = findViewById(R.id.computerScienceList);
        computerScienceBooks.setHasFixedSize(true);
        computerBooksList = new ArrayList<Books>();
        computerScienceBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        computerAdapter = new myAdapter2(this, computerBooksList,this);
        computerScienceBooks.setAdapter(computerAdapter);


        MechanicalBooks = findViewById(R.id.mechanicalList);
        MechanicalBooks.setHasFixedSize(true);
        MechanicalBooksList = new ArrayList<Books>();
        MechanicalBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        MechanicalAdapter = new myAdapter2(this, MechanicalBooksList,this);
        MechanicalBooks.setAdapter(MechanicalAdapter);

        Button b3=(Button)findViewById(R.id.b3);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,Psychology.class);
                startActivity(intent);
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
                    computerBooksList.add(book);

                }

                computerAdapter.notifyDataSetChanged();
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
                    MechanicalBooksList.add(book);

                }

                MechanicalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onClick(View view, int position)
    {
        Books book = computerAdapter.list.get(position);
        Intent i = new Intent(getApplicationContext(),PDFViewer.class);
//        Log.d(TAG, "URL: " + book.getUrl());
        i.putExtra("URL", book.getUrl());
        i.putExtra("pdfName", book.getTitle());
        startActivity(i);
    }
}