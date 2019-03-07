package com.company.archapp


import android.graphics.Bitmap

import com.google.firebase.ml.vision.common.FirebaseVisionImage


class VisionImage {

    private fun imageFromBitmap(bitmap: Bitmap) {
        // [START image_from_bitmap]
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        // [END image_from_bitmap]
    }

}
