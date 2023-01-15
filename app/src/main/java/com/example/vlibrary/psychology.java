package com.example.vlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class psychology extends AppCompatActivity {
    ImageView ib2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psychology);
        ib2= findViewById(R.id.bk2);



        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method("Psychologyvlib.pdf");
            }
        });
    }

    public void method(String src)
    {
        Intent i = new Intent(psychology.this,PDFViewer.class);
        i.putExtra("source", src);
        startActivity(i);


    }
}