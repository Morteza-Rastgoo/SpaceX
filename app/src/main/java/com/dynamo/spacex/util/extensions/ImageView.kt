package com.dynamo.spacex.util.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dynamo.spacex.util.transform.CropCircleWithBorderTransformation
import com.dynamo.spacex.util.transform.RoundedCornersTransformation

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * set tint to drawable resource with any color
 */
fun ImageView.applyDrawableTint(context: Context, @DrawableRes drawableRes: Int, color: Int) {
    val drawable = AppCompatResources.getDrawable(
        context,
        drawableRes
    )
    var wrappedDrawable: Drawable? = null
    drawable?.let { wrappedDrawable = it.mutate() }
    wrappedDrawable = wrappedDrawable?.let {
        DrawableCompat.wrap(
            it
        )
    }
    wrappedDrawable?.let {
        DrawableCompat.setTint(it, color)
        DrawableCompat.setTintMode(
            it,
            PorterDuff.Mode.SRC_IN
        )
    }
    this.setImageDrawable(wrappedDrawable)
}

/**
 * loading image from resource id
 */
fun ImageView.loadUrl(resourceID: Int, isCircle: Boolean = false, radius: Int = 0) {
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
        .load(resourceID)
        .apply(
            RequestOptions.bitmapTransform(
                transformation
            )
        )
        .into(this)
}

/**
 * loading image from url
 */
fun ImageView.loadUrl(
    url: String?,
    isCircle: Boolean = false,
    radius: Int = 0,
    isSvg: Boolean = false,
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
        if (isSvg) {
            /*GlideApp.with(this).`as`(PictureDrawable::class.java)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .apply(RequestOptions.bitmapTransform(transformation))
                .into(this)*/
        } else {
            Glide.with(this)
                .load(url)
                .placeholder(placeHolder)
                .apply(RequestOptions.bitmapTransform(transformation))
                .into(this)
        }
    }
}


/**
 * loading image from url with placeholder
 */
fun ImageView.loadUrl(url: String?, placeHolderDrawable: Drawable) {
    if (url != null && url.isNotEmpty())
        Glide.with(this).load(url).placeholder(placeHolderDrawable).into(this)
    else
        this.setImageDrawable(placeHolderDrawable)
}

/**
 * loading image from url without fading effect
 */
fun ImageView.loadUrlWithNoFade(url: String?) {
    if (url != null && url.isNotEmpty())
        Glide.with(this).load(url).dontAnimate().into(this)
}

/**
 * loading image from url without fading effect and with placeholder
 */
fun ImageView.loadUrlWithNoFade(url: String?, placeHolderDrawable: Drawable) {
    if (url != null && url.isNotEmpty())
        Glide.with(this).load(url).dontAnimate().placeholder(placeHolderDrawable).into(this)
    else
        this.setImageDrawable(placeHolderDrawable)
}