package com.dynamo.spacex.util.extensions

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.ReadOnlyProperty

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * Get color from resources
 */
fun Fragment.getColor(resId: Int) =
    ContextCompat.getColor(requireActivity(), resId)

fun Fragment.showSnackBar(it: String) {
    if (isAdded)
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
}

/**
 * Get color from attributes
 */
@ColorInt
@SuppressLint("Recycle")
fun Fragment.getColorFromAttr(
    @AttrRes themeAttrId: Int
): Int {
    return requireActivity().obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, 0)
    }
}

/**
 * change status bar color
 */
fun Fragment.setStatusBarColor(color: Int) {
    requireActivity().window.statusBarColor = color
}

/**
 * change status bar light mode
 */
fun Fragment.setLightStatusBar(on: Boolean) {
    if (on) {
        val view: View = requireActivity().window.decorView
        view.systemUiVisibility =
            view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        val view: View = requireActivity().window.decorView
        view.systemUiVisibility =
            view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}

/**
 * Used for adding a callback to activity onBackPressed
 */
fun Fragment.onBackPressed(callback: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback.invoke()
            }
        })
}

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(parentFragment!!, provider).get(VM::class.java)

/**
 * Provide a view model with nav graph
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.navGraphViewModelsDefaultFactory(
    @IdRes navGraphId: Int
): Lazy<VM> {
    return navGraphViewModels(navGraphId) { defaultViewModelProviderFactory }
}

/**
 * Create new [ViewBinding] associated with the [Fragment][this]
 */
@Suppress("unused")
inline fun <reified T : ViewBinding> Fragment.viewBinding(
    noinline viewBinder: (Fragment) -> T = FragmentViewBinder(
        T::class.java
    )::bind
): ReadOnlyProperty<Fragment, T> {
    return FragmentViewBindingProperty(
        viewBinder
    )
}


/**
 * get screen width and height -> Pair(first, second) = first: width , second: height
 */
fun Fragment.getDisplayMetrics(): Pair<Int, Int> {
    val displayMetrics = DisplayMetrics()
    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
    return Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
}
