package com.company.archapp.image;

import com.company.archapp.image.img.ImageDownloader;

import java.io.IOException;


/**
 * Download images from ethernet and paste them into discrete scroll view and insert as first element google map
 */
public class ImagesFromEthernet {

    private ImageDownloader myImg;
    private String[] urls = new String[4];

    public String[] putNameOfLandmarkToImage(final String name) {
        // Running in thread because android does not allowed to execute retrofit synchronised call in main thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get response
                    myImg = NetworkService.getInstance()
                            .getImgApi()
                            .getAPIImg(name.toLowerCase(), "bbc729574cf689f07871432a577fe2fc411814be5f1f13a6fb09f2e2d614c1df").execute().body();

                    // Get urls of images
                    for (int i = 0; i < urls.length; i++)
                        urls[i] = (myImg != null ? myImg.getResults()[i].getUrls().getRegular() : "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            // Waiting for finish thread
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
