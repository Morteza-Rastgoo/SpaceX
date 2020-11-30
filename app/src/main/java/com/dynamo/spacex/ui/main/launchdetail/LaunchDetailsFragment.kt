package com.dynamo.spacex.ui.main.launchdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.dynamo.spacex.R
import com.dynamo.spacex.databinding.LaunchDetailsFragmentBinding
import com.dynamo.spacex.ui.base.BaseFragment
import com.dynamo.spacex.util.extensions.gone
import com.dynamo.spacex.util.extensions.loadUrl
import com.dynamo.spacex.util.extensions.viewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
class LaunchDetailsFragment : BaseFragment(R.layout.launch_details_fragment) {
    private val binding: LaunchDetailsFragmentBinding by viewBinding()
    private val args: LaunchDetailsFragmentArgs by navArgs()

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            args.pastLaunch.apply {
                imageView.loadUrl(imageLink, isCircle = true, placeHolder = R.drawable.ic_launcher_foreground)
                textViewName.text = missionName
                textViewDate.text = date
                textViewDescription.text = description
                youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(player: YouTubePlayer) {
                        if (youtubeId != null) {
                            player.cueVideo(youtubeId, 0f)
                        } else {
                            youtubePlayer.gone()
                        }
                    }
                })
            }
        }
    }

}