package com.dynamo.spacex.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dynamo.spacex.R
import com.dynamo.spacex.ui.base.BaseFragment

class LaunchesFragment : BaseFragment(R.layout.fragment_launches) {

    companion object {
        fun newInstance() = LaunchesFragment()
    }

    val viewModel: LaunchesViewModel by viewModels()

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pastLaunches.observe(viewLifecycleOwner,  {


        })
        viewModel.getPastLaunches()
    }

}