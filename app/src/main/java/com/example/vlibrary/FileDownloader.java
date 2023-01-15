package com.VIT.vlibrary;

import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileDownloader {


    private static final int MEGABYTE = 1024*1024;

    public static void downloadFile(String fileUrl, File directory){
        try{
//            Log.d("TAG", "fileUrl: " + fileUrl);
            URL url = new URL(fileUrl);
//            URL url = new URL("https://firebasestorage.googleapis.com/v0/b/vlibrary-fc171.appspot.com/o/Finance%2FManaging%20Your%20Money.pdf?alt=media&token=d5ab60f2-a594-484c-b7c7-ff2f7ffc0465");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

//            Log.d("TAG", "directory: " + directory.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;


            while((bufferLength = inputStream.read(buffer))>0)
            {
                fileOutputStream.write(buffer,0,bufferLength);

            }

            fileOutputStream.close();

        }catch (FileNotFoundException e){e.printStackTrace();}

         catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
