package com.dynamo.spacex.util.extensions

import android.annotation.SuppressLint
import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * we use this for showing an simple toast on screen
 */
fun Context.toast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

/**
 * Get color from attributes
 */
@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

/**
 * copping the string to system clipboard
 */
fun Context.copyToClipboard(message: String) {
    val clipboard: ClipboardManager =
        this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip: ClipData =
        ClipData.newPlainText("simple text", message)
    clipboard.setPrimaryClip(clip)
}

/**
 * getting copied string from clipboard
 */
fun Context?.getTextFromClipboard(): String {
    val clipboardManager = this?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
        clipboardManager.primaryClipDescription?.let { clipDescription ->
            if (clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                val item = clipboardManager.primaryClip?.getItemAt(0)
                return item?.text?.toString() ?: ""
            }
        }
    }
    return ""
}

/**
 * opening some app in play store
 */
fun Context.openAppInStores(packageName: String) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
    } catch (exception: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}

/**
 * opening a url in application that can show web sites
 */
fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
        .apply {
            data = Uri.parse(url)
        }
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
//        toast(getString(R.string.msg_no_application_to_handle_intent))
    }
}

/**
 * sharing some text with title and description with any application
 */
fun Context.shareText(title: String, description: String) {
    val sharingIntent = Intent(Intent.ACTION_SEND)
        .apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, description)
        }
    this.startActivity(
        Intent.createChooser(
            sharingIntent,
            title
        )
    )
}

/**
 * getting the application theme id
 */
fun Context.getThemeId(): Int {
    try {
        val wrapper = Context::class.java
        val method = wrapper.getMethod("getThemeResId")
        method.isAccessible = true
        return method.invoke(this) as Int
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 0
}

/**
 * getting device display metric that cn be used for height and width
 */
fun Context.getDeviceMetrics(): DisplayMetrics {
    val metrics = DisplayMetrics()
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as (WindowManager)
    val display = wm.defaultDisplay
    display.getMetrics(metrics)
    return metrics
}

/**
 * getting Dimension from dimens.xml
 * Retrieve a dimensional for a particular resource ID. Unit conversions are based on the current
 * DisplayMetrics associated with the resources.
 *
 * Note about last divide: If you want exact dp value just as in xml just divide it with DisplayMetrics density
 */
fun Context.getDimension(id: Int): Float {
    return (resources.getDimension(id) / resources.displayMetrics.density)
}

/**
 * getScreenWidth
 */
fun Context.getScreenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * getScreenWidth
 */
fun Context.getScreenHeight(): Int {
    return resources.displayMetrics.heightPixels
}

/**
 * Check if network is available
 */
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

/**
 * Get color from attributes
 */
@ColorInt
@SuppressLint("Recycle")
fun Context.getColorFromAttr(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, 0)
    }
}