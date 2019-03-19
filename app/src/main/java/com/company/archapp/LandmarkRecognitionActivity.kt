package com.company.archapp

import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class LandmarkRecognitionActivity {

    companion object {
        var nameOfLandmark: String? = null

        fun recognizeLandmarksCloud(image: FirebaseVisionImage): String? {

            // Параметры Vision Cloud
            val options = FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(10)
                .build()

            // Начало процедуры распознавания
            val detector = FirebaseVision.getInstance()
                .visionCloudLandmarkDetector

            // Старт процедуры распознавания
            val result = detector.detectInImage(image)
                .addOnSuccessListener { firebaseVisionCloudLandmarks ->

                    // Старт исключения
                    // Получение название и координат
                    for (landmark in firebaseVisionCloudLandmarks) {

                        val bounds = landmark.boundingBox

                        //Название достопримечательности.
                        /** landmarkName это переменая в которую помешаеться название обекта **/
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
                }
                .addOnFailureListener {
                    // Задача не выполнена с исключением
                }
            return nameOfLandmark
        }
    }
}