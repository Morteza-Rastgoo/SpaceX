package com.dynamo.spacex.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dynamo.spacex.SpaceXApplication
import com.dynamo.spacex.di.ViewModelFactory
import com.dynamo.spacex.di.component.AppComponent
import javax.inject.Inject

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
abstract class BaseFragment(resID: Int) : Fragment(resID) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun initInjection()

    fun getAppComponent(): AppComponent {
        return (requireActivity().application as SpaceXApplication).appComponent
    }

    /**
     * This method androidx uses for `by viewModels` method.
     * We can set out injecting factory here and therefore don't touch it again
     */
    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
        viewModelFactory.create(this, arguments)

}