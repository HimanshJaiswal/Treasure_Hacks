package com.VIT.vlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFViewer extends AppCompatActivity {

    public void onBackPressed()
    {
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
        finish();
    }
    TextToSpeech textToSpeech;
    final Handler handler = new Handler();
    String url;
    String pdfName;
    PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        Intent i = getIntent();
        url = i.getStringExtra("URL");
        pdfName = i.getStringExtra("pdfName");

        DownloadPdffromURL();

        pdfView=findViewById(R.id.pdfView);
//        pdfView.fromAsset(source)

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int i)
            {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.CANADA);
                }
            }
        });



        ImageButton mic = (ImageButton)findViewById(R.id.imageButton);
        mic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }
                else
                {
                    try {
                        extractPDF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPdf();            }
        }, 5000);


    }



    private void DownloadPdffromURL()
    {
        new downloadPDF().execute(url,pdfName);
        return ;
    }


    public void viewPdf()
    {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/DownloadedPdfs/" +pdfName+".pdf" );
        Log.d("pdfShown", "viewPdf: " + pdfFile.toString());
        pdfView.fromFile(pdfFile)
            .spacing(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(false)
            .password(null)
            .scrollHandle(new DefaultScrollHandle(this))
            .enableAntialiasing(true)

//            .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
            .spacing(10)// add dynamic spacing to fit each page on its own on the screen

//            .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
//            .pageSnap(false) // snap pages to screen boundaries
//            .pageFling(false) // make a fling change only a single page like ViewPager
//            .nightMode(false) //toggle night mode
            .load();

        Log.d("TAG", "viewPdf: pdfLoaded?");
    }


    public void extractPDF() throws IOException {
        try
        {
            Log.d("TAG", "extractPDF: start");
            // creating a string for
            // storing our extracted text.
            String extractedText = "";

            // creating a variable for pdf reader
            // and passing our PDF file in it.
//            PdfReader reader = new PdfReader("assets/"+source );
            PdfReader reader = new PdfReader(Environment.getExternalStorageDirectory() + "/DownloadedPdfs/" + pdfName + ".pdf");
//            Toast.makeText(getApplicationContext(),"reading mode on"+reader,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Narrator Spaeking",Toast.LENGTH_SHORT).show();
            // below line is for getting number
            // of pages of PDF file.
            int n = reader.getNumberOfPages();
            Log.d("TAG", "extractPDF: " + n);
            // running a for loop to get the data from PDF
            // we are storing that data inside our string.
            for (int i = 0; i < n; i++)

            {
                try {
                    extractedText = PdfTextExtractor.getTextFromPage(reader, i +1).trim() + "\n";
                    textToSpeech.speak(extractedText,TextToSpeech.QUEUE_ADD,null);
//                    Log.d("Debug.....!",extractedText);
                }catch (Exception e){
                    extractedText = PdfTextExtractor.getTextFromPage(reader, i).trim() + "\n";
                    textToSpeech.speak(extractedText,TextToSpeech.QUEUE_FLUSH,null);
//                    Log.d("Debug.....!",extractedText);
                }
                // to extract the PDF content from the different pages-
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}