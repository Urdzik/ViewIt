package com.company.archapp;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.company.archapp.img.ImageDownloader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity {

    private Log myLog;
    private final static String TAG = "MyActivity";

    public void putNameOfLandmarkToImage(String name, final ImageView imageView, final TextView textView) {

        NetworkService.getInstance()
                .getImgApi()
                .getAPIImg(name.toLowerCase(), "bbc729574cf689f07871432a577fe2fc411814be5f1f13a6fb09f2e2d614c1df")
                .enqueue(new Callback<ImageDownloader>() {
                    @Override
                    public void onResponse(Call<ImageDownloader> call, Response<ImageDownloader> response) {
                        if (response.isSuccessful()) {
                            ImageDownloader myImg = response.body();
                            if(myImg.getResults().length!=0) {
                                String url1 = myImg.getResults()[0].getUrls().getFull();
                                showImage(url1, imageView, textView);
                            }else{
                                myLog.d(TAG, "None results");
                            }
                        } else {
                            myLog.d(TAG, "Query error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageDownloader> call, Throwable t) {
                        myLog.d(TAG, t.getMessage());
                    }
                });
    }

    public void showImage(String url, ImageView imageView, TextView textView) {
        if(!url.equals(null)){
            Glide.with(imageView).load(url).into(imageView);
        } else {
            textView.setText("Sorry...");
        }
    }



}
