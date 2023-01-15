package com.VIT.vlibrary;

import static com.VIT.vlibrary.Register.TAG;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CurrentlyReading extends AppCompatActivity implements MyAdapter.ItemClickListener {
    Button view;
    DatabaseReference database;
    String message;

    MyAdapter myAdapter;
    ArrayList<Books> list;
    RecyclerView recyclerView;


//    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        recyclerView = findViewById(R.id.bookslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        list = new ArrayList<Books>();

        myAdapter = new MyAdapter(this,list,this);
        recyclerView.setAdapter(myAdapter);
//        MyAdapter.setClickListener(this);


//        Working horizontal reCyclerView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        database = FirebaseDatabase.getInstance(" https://vlibrary-fc171-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Finance");
//      l = (ListView) findViewById(R.id.currentlyReadingList);

//        ArrayList<String> currentlyReadingList = new ArrayList<>();
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.books_list,currentlyReadingList);
//        l.setAdapter(adapter);


//        Toast.makeText(getApplicationContext(),"Books added: ",Toast.LENGTH_LONG).show();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                currentlyReadingList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
//                    Books book = new Books();
//                    book.setAuthor(snapshot.child("Author").getValue(String.class));
//                    book.setTitle(snapshot.child("Title").getValue(String.class));
//                    Log.d("TAG", "Books added: " + book.getAuthor()+book.getTitle());
//                    book.setYear(snapshot.child("Year").getValue(String.class));
//                    book.setUrl(snapshot.child("url").getValue(String.class));
                    Books book = snapshot.getValue(Books.class);
                    list.add(book);


//                    BookInfo info = snapshot.getValue(BookInfo.class);
//                    String txt = "Author: " + info.getAuthor();
//                    currentlyReadingList.add(txt);
                }

                myAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//      DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL);
//      recyclerView.addItemDecoration(itemDecor);

//      view = findViewById(R.id.view);

//        database.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // getting a DataSnapshot for the location at the specified
//                // relative path and getting in the link variable
//                message = dataSnapshot.getValue(String.class);
//            }

            // this will get called when any problem
            // occurs in getting data
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // we are showing that error message in toast
//                Toast.makeText(CurrentlyReading.this, "Error Loading Pdf", Toast.LENGTH_SHORT).show();
//            }
//        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                CharSequence options[] = new CharSequence[]{
//                        "Download",
//                        "View",
//                        "Cancel"
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("Choose One");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // we will be downloading the pdf
//                        if (which == 0) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
//                            startActivity(intent);
//                        }
//                        // We will view the pdf
//                        if (which == 1) {
//                            Log.e("error in message", message);
//                            Intent intent = new Intent(v.getContext(), Viewpdf.class);
//                            intent.putExtra("url", message);
//                            startActivity(intent);
//                        }
//                    }
//                });
//                builder.show();
//            }
//        });

    }

    @Override
    public void onClick(View view, int position) {

        Books book = myAdapter.list.get(position);
        Intent i = new Intent(getApplicationContext(),PDFViewer.class);
//        Log.d(TAG, "URL: " + book.getUrl());
        i.putExtra("URL", book.getUrl());
        i.putExtra("pdfName", book.getTitle());
        startActivity(i);
    }




}