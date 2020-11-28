package com.dynamo.spacex.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.dynamo.spacex.R
import com.dynamo.spacex.ui.base.BaseFragment

class MainFragment : BaseFragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()

    override fun initInjection() {
        getAppComponent().mainComponent().create().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}