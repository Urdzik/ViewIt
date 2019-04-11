package com.company.archapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.company.archapp.img.ImageSearch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String name;

    //B метод передаём название достопремечательности

    public void getNameOfLandmarkToImage(String name) {
        this.name = name;
    }

    private ImageView imageView1;
    private Log myLog;
    private final static String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        NetworkService.getInstance()
                .getImgApi()
                .getAPIImg(name, "images", "images", "json")
                .enqueue(new Callback<ImageSearch>() {                                              //делаем запрос на сервер по нэйму
                    @Override
                    public void onResponse(Call<ImageSearch> call, Response<ImageSearch> response) {
                        if (response.isSuccessful()) {
                            ImageSearch myImg = response.body();
                            //находим из POJO-класса(класс, в который передаётся результат запроса) ссылку на картинки и передаём их в метод ShowImage()
                            if(myImg.getRelatedTopics().length!=0) {
                                String url1 = myImg.getRelatedTopics()[0].getIcon().getURL();
                                showImage(url1);//передали URL в метод, где мы отображаем картинку
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
                        showImage("Fail server response");
                    }
                });
    }

    private void showImage(String url1) {

        imageView1 = findViewById(R.id.info_image);

        myLog.d(TAG, url1);

        Glide.with(this).load(url1).into(imageView1);
    }

}
