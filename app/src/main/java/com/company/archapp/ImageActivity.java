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
    private ImageDownloader myImg;
    public String urls[] = new String[6];

    public String[] putNameOfLandmarkToImage(String name, final ImageView imageView, final TextView textView) {

        NetworkService.getInstance()
                .getImgApi()
                .getAPIImg(name.toLowerCase(), "bbc729574cf689f07871432a577fe2fc411814be5f1f13a6fb09f2e2d614c1df")
                .enqueue(new Callback<ImageDownloader>() {
                    @Override
                    public void onResponse(Call<ImageDownloader> call, Response<ImageDownloader> response) {
                        if (response.isSuccessful()) {
                            myImg = response.body();

                            for (int i = 0; i < urls.length; i++)
                                urls[i] = myImg.getResults()[i].getUrls().getRegular();

                            myLog.d(TAG, "URL = " + urls[0]);
                            showImage(urls, imageView, textView);

                        } else {
                            myLog.d(TAG, "Query error");
                            textView.setText("No results...");
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageDownloader> call, Throwable t) {
                        myLog.d(TAG, t.getMessage());
                        textView.setText("No results...");
                    }
                });
        return urls;
    }

    private void showImage(String[] url, ImageView imageView, TextView textView) {
        if(!url.equals(null)){
            Glide.with(imageView).load(url[0]).into(imageView);
        } else {
            textView.setText("Sorry, no results...");
        }
    }


  /*    if(myImg.getResults().length!=0) {
                                //String url1 = myImg.getResults()[0].getUrls().getRegular();
                                //showImage(url1, imageView, textView);
                            }else{
                                myLog.d(TAG, "None results");
                                textView.setText("No results...");
                            }*/



}
