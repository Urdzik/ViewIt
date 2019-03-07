package com.company.archapp

import android.support.v7.app.AppCompatActivity
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage




class LandmarkRecognitionActivity : AppCompatActivity() {


    companion object {
        var nameOfLandmark: String = "Name Of Landmark"

         fun recognizeLandmarksCloud(image: FirebaseVisionImage): String {

            // [START set_detector_options_cloud]
            val options = FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(30)
                .build()
            // [END set_detector_options_cloud]

            // [START get_detector_cloud]
            val detector = FirebaseVision.getInstance()
                .visionCloudLandmarkDetector

            // [START run_detector_cloud]
            val result = detector.detectInImage(image)
                .addOnSuccessListener { firebaseVisionCloudLandmarks ->
                    // Задача успешно выполнена
                    // [START_EXCLUDE]
                    // [START get_landmarks_cloud]
                    for (landmark in firebaseVisionCloudLandmarks) {

                        val bounds = landmark.boundingBox
                        //Имя достопримечательности
                        val landmarkName = landmark.landmark

                        nameOfLandmark = landmarkName


                        val entityId = landmark.entityId
                        val confidence = landmark.confidence

                        // Возможны несколько местоположений, например, местоположение изображенного
                        // Ориентир и место, где был сделан снимок.
                        for (loc in landmark.locations) {
                            val latitude = loc.latitude
                            val longitude = loc.longitude


                        }
                    }
                    // [END get_landmarks_cloud]
                    // [END_EXCLUDE]
                }
                .addOnFailureListener {
                    // Задача не выполнена с исключением
                    // ...

                }
            // [END run_detector_cloud]
             return nameOfLandmark


        }
    }
}