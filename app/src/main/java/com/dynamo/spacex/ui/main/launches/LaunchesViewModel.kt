package com.dynamo.spacex.ui.main.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.data.usecase.launches.GetPastLaunchesUseCase
import com.dynamo.spacex.ui.base.BaseViewModel
import com.dynamo.spacex.ui.base.ViewState
import com.dynamo.spacex.util.extensions.doOnSuccess
import com.dynamo.spacex.util.extensions.notifyObservers
import javax.inject.Inject

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
class LaunchesViewModel @Inject constructor(private val getPastLaunchesUseCase: GetPastLaunchesUseCase) :
    BaseViewModel() {
    /**
     * List of Past Launches of SpaceX
     */
    private val _pastLaunches = MutableLiveData<MutableList<PastLaunch>>(mutableListOf())
    val pastLaunches: LiveData<MutableList<PastLaunch>>
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
    fun getPastLaunches() {
        val offset = currentPage * dataLimit
        launchDataLoad(if (currentPage == 0) ViewState.LOADING else ViewState.LOAD_MORE) {
            getPastLaunchesUseCase.invoke(offset).doOnSuccess {
                _pastLaunches.apply {
                    value?.addAll(it)
                    notifyObservers()
                }
                _currentPage++
            }
        }
    }

}
