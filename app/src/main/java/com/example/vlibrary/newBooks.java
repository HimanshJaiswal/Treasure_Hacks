package com.VIT.vlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newBooks extends AppCompatActivity {

    private DatabaseReference mDatabase;
// ...



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_books);

        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
}