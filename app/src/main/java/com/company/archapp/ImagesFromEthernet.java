package com.company.archapp;

import android.content.Context;
import android.util.Log;
import com.company.archapp.img.ImageDownloader;
import com.google.android.gms.maps.model.LatLng;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ImagesFromEthernet {

    private final static String TAG = "MyActivity";
    private ImageDownloader myImg;
    private String[] urls = new String[4];

    public String[] putNameOfLandmarkToImage(String name, final DiscreteScrollView discreteScrollView, final Context context, final LatLng latLng) {

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

                            generateDataForDSV(urls, discreteScrollView, context, latLng);
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

    private void generateDataForDSV(String[] urls, DiscreteScrollView discreteScrollView, Context context, LatLng latLng) {

        List<LandmarkContentItem> landmarkContentItems = new ArrayList<>();

        landmarkContentItems.add(new LandmarkContentItem(latLng));
        for (String url : urls)
            landmarkContentItems.add(new LandmarkContentItem(url));

        LandmarkContentAdapter adapter = new LandmarkContentAdapter(landmarkContentItems, context);
        discreteScrollView.setAdapter(adapter);
    }
}
