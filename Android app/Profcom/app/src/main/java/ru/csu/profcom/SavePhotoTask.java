package ru.csu.profcom;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class SavePhotoTask extends AsyncTask<byte[], String, String> {
    private String fileName;
    private Context context;

    public SavePhotoTask(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    @Override
    protected String doInBackground(byte[]... jpeg) {
        File photo = new File(Environment.getExternalStorageDirectory(), this.fileName);
        //File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + this.fileName);

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos=new FileOutputStream(photo.getPath());

            fos.write(jpeg[0]);
            fos.close();
        }
        catch (java.io.IOException e) {
            Log.e("PictureDemo", "Exception in photoCallback", e);
        }
        return(null);
    }
}

