package com.dynamo.spacex.util.extensions

import android.content.res.Resources
import kotlin.math.roundToInt

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * converts dp to pixels based on display metrics

 * @param dp dp that should be convert to pixels
 * *
 * @return int value
 */
fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * converts pixels to dp based on display metrics

 * @param px pixels that should be convert to dp
 * *
 * @return float value
 */
fun Float.pxToDp(): Float =
    this / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / 160f)