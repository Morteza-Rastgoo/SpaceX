package com.dynamo.spacex.ui.main.launchdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dynamo.spacex.R
import com.dynamo.spacex.databinding.LaunchDetailsFragmentBinding
import com.dynamo.spacex.ui.base.BaseFragment
import com.dynamo.spacex.util.extensions.loadUrl
import com.dynamo.spacex.util.extensions.viewBinding

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
class LaunchDetailsFragment : BaseFragment(R.layout.launch_details_fragment) {
    private val viewModel: LaunchDetailsViewModel by viewModels()
    private val binding: LaunchDetailsFragmentBinding by viewBinding()
    private val args: LaunchDetailsFragmentArgs by navArgs()

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            args.pastLaunch.apply {
                imageView.loadUrl(imageLink, isCircle = true)
                textViewName.text = missionName
                textViewDate.text = date
                textViewDescription.text = description
            }
        }
    }

}