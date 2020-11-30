package com.dynamo.spacex.ui.main.launches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamo.spacex.R
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.databinding.FragmentLaunchesBinding
import com.dynamo.spacex.ui.base.BaseFragment
import com.dynamo.spacex.ui.base.ViewState.*
import com.dynamo.spacex.ui.main.launchdetail.LaunchDetailsFragmentArgs
import com.dynamo.spacex.util.extensions.gone
import com.dynamo.spacex.util.extensions.viewBinding
import com.dynamo.spacex.util.extensions.visible
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
class LaunchesFragment : BaseFragment(R.layout.fragment_launches) {

    private val viewModel: LaunchesViewModel by viewModels()
    private val binding: FragmentLaunchesBinding by viewBinding()
    private lateinit var fastItemAdapter: GenericFastItemAdapter
    private lateinit var footerAdapter: GenericItemAdapter
    private lateinit var scrollListener: EndlessRecyclerOnScrollListener

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observePastLaunches()
        observeViewState()
    }

    private fun initRecyclerView() {
        //create our FastAdapter which will manage everything
        fastItemAdapter = FastItemAdapter()

        //create our FooterAdapter which will manage the progress items
        footerAdapter = items()
        fastItemAdapter.addAdapter(1, footerAdapter)

        //configure onclick
        fastItemAdapter.onClickListener = { v, _, item, _ ->
            findNavController().navigate(
                R.id.action_mainFragment_to_launchDetailsFragment, LaunchDetailsFragmentArgs(
                    item as PastLaunch
                ).toBundle()
            )
            true
        }

        //get our recyclerView and do basic setup
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            itemAnimator = DefaultItemAnimator()
            adapter = fastItemAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
            scrollListener = object : EndlessRecyclerOnScrollListener(footerAdapter) {
                override fun onLoadMore(currentPage: Int) {
                    footerAdapter.clear()
                    val progressItem = ProgressItem()
                    progressItem.isEnabled = false
                    footerAdapter.add(progressItem)
                    viewModel.getPastLaunches()
                }
            }
            addOnScrollListener(scrollListener)
        }
    }

    /**
     * Listen to the pastLaunches and update recyclerView adapter
     */
    private fun observePastLaunches() {
        viewModel.pastLaunches.observe(viewLifecycleOwner, {
            footerAdapter.clear()
            fastItemAdapter.setNewList(it)
        })
        viewModel.apply {
            if (pastLaunches.value?.size == 0)
                getPastLaunches()
        }
    }

    /**
     * Listen to the viewState and change UI
     */
    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, {
            binding.apply {
                when (it) {
                    LOADING -> {
                        loading.root.visible()
                    }
                    SHOW_DATA -> {
                        loading.root.gone()
                    }
                    NO_INTERNET -> {
                        loading.root.gone()
                        error.root.visible()
                        error.icon.setImageResource(R.drawable.ic_baseline_wifi_off_24)
                        error.textViewError.text = getString(R.string.no_internet_connection)
                    }
                    GENERAL_ERROR -> {
                        loading.root.gone()
                        error.root.visible()
                        error.icon.setImageResource(R.drawable.ic_baseline_error_outline_24)
                        error.textViewError.text = getString(R.string.sorry_something_went_wrong)
                    }
                    LOAD_MORE -> {
                        //Do nothing
                    }
                    else -> {
                        throw IllegalArgumentException("Unknown view state.")
                    }
                }
            }
        })
    }

    /**
     * Release callbacks to avoid leaks
     */
    override fun onDestroy() {
        binding.recyclerView.removeOnScrollListener(scrollListener)
        fastItemAdapter.onClickListener = null
        super.onDestroy()
    }
}