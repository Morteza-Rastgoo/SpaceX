package com.dynamo.spacex.util.extensions

import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamo.spacex.util.transform.CropCircleWithBorderTransformation
import com.dynamo.spacex.util.transform.RoundedCornersTransformation

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * loading image from url
 */
fun ImageView.loadUrl(
    url: String?,
    isCircle: Boolean = false,
    radius: Int = 0,
    placeHolder: Int = 0
) {
    if (url != null && url.isNotEmpty()) {
        val transformation = if (isCircle) {
            CropCircleWithBorderTransformation(
                0,
                Color.WHITE
            )
        } else {
            RoundedCornersTransformation(
                radius,
                0,
                RoundedCornersTransformation.CornerType.ALL
            )
        }
            Glide.with(this)
                .load(url)
                .placeholder(placeHolder)
                .apply(RequestOptions.bitmapTransform(transformation))
                .into(this)
    }
}