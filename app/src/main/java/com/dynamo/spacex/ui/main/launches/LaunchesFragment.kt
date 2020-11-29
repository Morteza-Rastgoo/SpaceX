package com.dynamo.spacex.ui.main.launches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamo.spacex.R
import com.dynamo.spacex.databinding.FragmentLaunchesBinding
import com.dynamo.spacex.ui.base.BaseFragment
import com.dynamo.spacex.util.extensions.viewBinding
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem

class LaunchesFragment : BaseFragment(R.layout.fragment_launches) {

    private val viewModel: LaunchesViewModel by viewModels()
    private val binding: FragmentLaunchesBinding by viewBinding()
    private lateinit var fastItemAdapter: GenericFastItemAdapter
    private lateinit var footerAdapter: GenericItemAdapter

    override fun initInjection() {
        getAppComponent().launchesComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observePastLaunches()
    }

    private fun initRecyclerView() {
        //create our FastAdapter which will manage everything
        fastItemAdapter = FastItemAdapter()

        //create our FooterAdapter which will manage the progress items
        footerAdapter = items()
        fastItemAdapter.addAdapter(1, footerAdapter)

        //configure onclick
        fastItemAdapter.onClickListener = { v, _, item, _ ->
            // TODO: 29/11/2020 AD Go to new page
            false
        }

        //get our recyclerView and do basic setup
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            itemAnimator = DefaultItemAnimator()
            adapter = fastItemAdapter
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            val listener = object : EndlessRecyclerOnScrollListener(footerAdapter) {
                override fun onLoadMore(currentPage: Int) {
                    footerAdapter.clear()
                    val progressItem = ProgressItem()
                    progressItem.isEnabled = false
                    footerAdapter.add(progressItem)
                    viewModel.getPastLaunches()
                }
            }
            addOnScrollListener(listener)
        }
    }

    private fun observePastLaunches() {
        viewModel.pastLaunches.observe(viewLifecycleOwner, {
            footerAdapter.clear()
            fastItemAdapter.setNewList(it)

        })
        lifecycleScope.launchWhenCreated {
            viewModel.getPastLaunches()
        }
    }

}