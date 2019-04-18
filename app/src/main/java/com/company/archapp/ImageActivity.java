package com.company.archapp;

import android.content.Context;
import android.util.Log;
import com.company.archapp.img.ImageDownloader;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity {

    private final static String TAG = "MyActivity";
    private ImageDownloader myImg;
    public String urls[] = new String[4];

    public String[] putNameOfLandmarkToImage(String name, final DiscreteScrollView discreteScrollView, final Context context) {

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

                            generateDataForDSV(urls, discreteScrollView, context);
                        } else {
                            Log.d(TAG, "Query error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageDownloader> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
        return urls;
    }

    private void generateDataForDSV(String[] urls, DiscreteScrollView discreteScrollView, Context context) {

        List<PhotoItem> photoItems = new ArrayList<>();
        for (String url : urls)
            photoItems.add(new PhotoItem(url));

        PhotosAdapter adapter = new PhotosAdapter(photoItems, context);
        discreteScrollView.setAdapter(adapter);
    }

  /*    if(myImg.getResults().length!=0) {
                                //String url1 = myImg.getResults()[0].getUrls().getRegular();
                                //showImage(url1, imageView, textView);
                            }else{
                                Log.d(TAG, "None results");
                                textView.setText("No results...");
                            }*/



}
