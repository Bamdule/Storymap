package com.storymap.network;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageAsync extends AsyncTask<Void, Integer,Bitmap> {
    ImageView imageView=null;
    URL url=null;
    NetworkManager networkManager=NetworkManager.getInstance();

    public ImageAsync(ImageView imageView , URL url){
        this.url=url;
        this.imageView=imageView;
    }
    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap bitmap = null;
        bitmap = networkManager.httpURLImageDownload(url);
        return bitmap;
    }
    protected void onPostExecute(Bitmap img){
        imageView.setImageBitmap(img);
    }
}