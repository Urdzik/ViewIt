package com.company.archapp.image;

import com.company.archapp.image.img.ImageDownloader;

import java.io.IOException;


/**
 * Download images from ethernet
 * Скачуем картинки из интернета
 */
public class ImagesFromEthernet {

    private ImageDownloader myImg;
    private final String[] urls = new String[4];

    @SuppressWarnings("UnusedReturnValue")
    public String[] putNameOfLandmarkToImage(final String name) {
        // Running in the thread because android does not allow to execute retrofit synchronized call in the main thread
        // Запуск в потоке, потому что Android не позволяет выполнить синхронизированный вызов дооснащения в основном потоке
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get response
                    // Получаем ответ
                    myImg = NetworkService.getInstance()
                            .getImgApi()
                            .getAPIImg(name.toLowerCase(), "bbc729574cf689f07871432a577fe2fc411814be5f1f13a6fb09f2e2d614c1df").execute().body();

                    // Get urls of images
                    // Получаем ссылки на картинки
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
            // Ждём завершения потока
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
