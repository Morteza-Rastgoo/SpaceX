package com.dynamo.spacex.util.transform

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.bumptech.glide.load.Key.CHARSET
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.dynamo.spacex.util.extensions.pxToDp
import java.security.MessageDigest

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/


class CropCircleWithBorderTransformation : BitmapTransformation {
    private var borderSize: Int
    private var borderColor: Int

    constructor() {
        borderSize = (4f).pxToDp().toInt()
        borderColor = Color.BLACK
    }

    constructor(borderSize: Int, @ColorInt borderColor: Int) {
        this.borderSize = borderSize
        this.borderColor = borderColor
    }

    override fun transform(
        context: Context, pool: BitmapPool,
        toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        val bitmap = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight)
        setCanvasBitmapDensity(toTransform, bitmap)
        val paint = Paint()
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSize.toFloat()
        paint.isAntiAlias = true
        val canvas = Canvas(bitmap)
        canvas.drawCircle(
            outWidth / 2f,
            outHeight / 2f,
            Math.max(outWidth, outHeight) / 2f - borderSize / 2f,
            paint
        )
        return bitmap
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(
            (ID + borderSize + borderColor).toByteArray(
                CHARSET
            )
        )
    }

    override fun equals(o: Any?): Boolean {
        return o is CropCircleWithBorderTransformation && o.borderSize == borderSize && o.borderColor == borderColor
    }

    override fun hashCode(): Int {
        return ID.hashCode() + borderSize * 100 + borderColor + 10
    }

    companion object {
        private const val VERSION = 1
        private const val ID =
            "jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation.$VERSION"
    }
}