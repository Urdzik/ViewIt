package com.company.archapp


import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        var tv: TextView = findViewById(R.id.landmarkNameone)
        tv.text = LandmarkRecognitionActivity.nameOfLandmark

        //Передаёт изображение в битмапу

        val bmp = BitmapFactory.decodeResource(resources, R.mipmap.blagovesh_1)
        val image = VisionImage.imageFromBitmap(bmp)
        LandmarkRecognitionActivity.recognizeLandmarksCloud(image!!)
    }

    val options = FirebaseVisionCloudDetectorOptions.Builder()
        .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
        .setMaxResults(15)
        .build()

}









