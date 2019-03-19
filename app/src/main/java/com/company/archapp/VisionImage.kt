package com.company.archapp


import android.graphics.Bitmap
import com.google.firebase.ml.vision.common.FirebaseVisionImage


class VisionImage {

    companion object {
        fun imageFromBitmap(bitmap: Bitmap): FirebaseVisionImage? {
            // старт Bitmap
            val image = FirebaseVisionImage.fromBitmap(bitmap)
            return image
            // конец Bitmap
        }
    }
}
