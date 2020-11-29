package com.dynamo.spacex.ui.main.launchdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dynamo.spacex.R
import com.dynamo.spacex.databinding.LaunchDetailsFragmentBinding
import com.dynamo.spacex.ui.base.BaseFragment
import com.dynamo.spacex.util.extensions.viewBinding

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
class LaunchDetailsFragment : BaseFragment(R.layout.launch_details_fragment) {
    private val viewModel: LaunchDetailsViewModel by viewModels()
    private val binding: LaunchDetailsFragmentBinding by viewBinding()

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}