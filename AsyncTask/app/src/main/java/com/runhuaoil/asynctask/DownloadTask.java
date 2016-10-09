package com.runhuaoil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by RunHua on 2016/10/8.
 */

public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private Context context = null;
    private ProgressDialog progressDialog;
    private static int downloadProgress = 0;



    public DownloadTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("下载进度");
        //progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
        downloadProgress =0;
        Log.d("Test", progressDialog.getProgress() + "");
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressDialog.cancel();
        if (aBoolean){
            Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        progressDialog.setProgress(values[0]);

        Log.d("Test", progressDialog.getProgress() + "");

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {



                publishProgress(inDownload());

                if (downloadProgress >= 100) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private int inDownload(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return downloadProgress = downloadProgress + 5;

    }
}
