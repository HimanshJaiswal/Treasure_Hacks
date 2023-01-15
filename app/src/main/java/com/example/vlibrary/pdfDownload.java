package com.VIT.vlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdfDownload extends AppCompatActivity {

    String url;
    PDFView pdfView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdf);
        pdfView = findViewById(R.id.abc);

        // Firstly we are showing the progress
        // dialog when we are loading the pdf
        dialog = new ProgressDialog(this);
        Log.i("launched PDFVIEW", "Loading ");
        dialog.setMessage("Loading..");
        dialog.show();

        // getting url of pdf using getItentExtra
        url = getIntent().getStringExtra("url");
        Log.i("launched PDFVIEW",  url);
        new RetrivePdfStream().execute(url);
    }

    // Retrieving the pdf file using url
    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {
//        Context context;

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {

                // adding url
                URL url = new URL(strings[0]);
//                String filename = strings[1];
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();


                // if url connection response code is 200 means ok the execute
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            // if error return null
            catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        // Here load the pdf and dismiss the dialog box
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
            dialog.dismiss();
        }
    }
}
