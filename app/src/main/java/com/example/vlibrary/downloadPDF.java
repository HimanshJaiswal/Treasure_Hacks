package com.VIT.vlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class downloadPDF extends AsyncTask<String, Void, Void> {

    Context ctx;


//if (Build.VERSION.SDK_INT >= 30){
//        if (!Environment.isExternalStorageManager()){
//            Intent getpermission = new Intent();
//            getpermission.setAction(Intent.ACTION_MANAGE_PACKAGE_STORAGE);
//            startActivity(getpermission);
//        }
//    }

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0];
        String fileName = strings[1];

//        Log.d("TAG", "fileURL: " + fileUrl);


//        File newfile = ctx.getExternalFilesDir(null);
//
//        Log.d("TAG", "newfile: " + newfile.getAbsolutePath());

//        File f = new File(System.getProperty("user.home")+"DownloadedPdfs");
//        if(!f.exists())
//        {
//            f.mkdirs();
//        }

//        if(isExternalStorageWritable())
//        {
//            Log.d("TAG", "isExternalStorageWritable: yes");
//        }
//        else
//        {
//            Log.d("TAG", "isExternalStorageWritable: no");
//
//        }


        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        Log.d("TAG", "extstorage before: " + extStorageDirectory);
        File folder = new File(extStorageDirectory,"DownloadedPdfs");
        folder.mkdirs();
//        Log.d("TAG", "extstorage after: " + folder.getAbsolutePath());

        String pdfName = fileName + ".pdf";
        File pdfFile = new File(folder,pdfName);
//        Log.d("TAG", "pdfFile: " + pdfFile.getAbsolutePath());
        try {
            if (pdfFile.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
//            pdfFile.createNewFile();
//            Log.d("TAGa", "pdfFile sizes: " + pdfFile.getFreeSpace());;
//        }catch (Exception e){
//            e.printStackTrace();
//        }



        FileDownloader.downloadFile(fileUrl,pdfFile);
        return null;
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
