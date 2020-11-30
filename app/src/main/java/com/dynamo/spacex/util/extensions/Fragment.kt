package com.dynamo.spacex.util.extensions

import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.navGraphViewModels
import androidx.viewbinding.ViewBinding
import com.dynamo.spacex.util.FragmentViewBinder
import com.dynamo.spacex.util.FragmentViewBindingProperty
import kotlin.properties.ReadOnlyProperty

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

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
