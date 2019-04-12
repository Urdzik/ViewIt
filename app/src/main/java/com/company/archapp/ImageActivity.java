package com.company.archapp;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.company.archapp.img.ImageSearch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity {
    
    private Log myLog;
    private final static String TAG = "MyActivity";

    public void putNameOfLandmarkToImage(String name, final ImageView imageView, final TextView textView) {

        NetworkService.getInstance()
            .getImgApi()
            .getAPIImg(name.toLowerCase(), "images", "images", "json")
            .enqueue(new Callback<ImageSearch>() {
                @Override
                public void onResponse(Call<ImageSearch> call, Response<ImageSearch> response) {
                    if (response.isSuccessful()) {
                        ImageSearch myImg = response.body();
                        if(myImg.getRelatedTopics().length!=0) {
                            String url1 = myImg.getRelatedTopics()[0].getIcon().getURL();
                            //showImage(url1);
                            showImage(url1, imageView, textView);
                        }else{
                          myLog.d(TAG, "None results");
                        }
                    } else {
                        myLog.d(TAG, "Query error");
                    }
                }

                @Override
                public void onFailure(Call<ImageSearch> call, Throwable t) {
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
