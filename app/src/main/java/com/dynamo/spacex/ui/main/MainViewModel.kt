package com.dynamo.spacex.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dynamo.spacex.data.repository.LaunchesRepository
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.ui.base.BaseViewModel
import com.dynamo.spacex.ui.base.ViewState
import javax.inject.Inject

// TODO: 29/11/2020 AD Rename it to LaunchesViewModel and rename all related code
class MainViewModel @Inject constructor(private val launchesRepository: LaunchesRepository) :
    BaseViewModel() {
    /**
     * List of Past Launches of SpaceX
     */
    private val _pastLaunches = MutableLiveData<List<PastLaunch>>()
    val pastLaunches: LiveData<List<PastLaunch>>
        get() = _pastLaunches

    /**
     * Number of items to load per page
     */
    private val dataLimit = 15

    /**
     * Current page of pagination
     */
    private var _currentPage = 0
    val currentPage: Int
        get() = _currentPage

    /**
     * Resets the pagination when needed
     */
    fun resetPagination(): Unit {
        _currentPage = 0
    }

    /**
     * Loads past launches. This will also handle the pagination.
     */
    fun getPastLaunches() =
        launchDataLoad(if (currentPage == 0) ViewState.LOADING else ViewState.LOAD_MORE) {
            val offset = currentPage * dataLimit
            _pastLaunches.value =
                launchesRepository.getPastLaunches(offset = offset, limit = dataLimit)
            _currentPage++
        }

}
