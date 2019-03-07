package com.company.archapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val options = FirebaseVisionCloudDetectorOptions.Builder()
            .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
            .setMaxResults(15)
            .build()

}

