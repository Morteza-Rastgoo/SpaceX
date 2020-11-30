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
    private var _currentPage = MutableLiveData(0)

    /**
     * Resets the pagination when needed
     */
    fun resetPagination() {
        _currentPage.value = 0
    }

    /**
     * Loads past launches. This will also handle the pagination.
     */
    fun getPastLaunches() {
        val offset = _currentPage.value!! * dataLimit
        launchDataLoad(if (_currentPage.value == 0) ViewState.LOADING else ViewState.LOAD_MORE) {
            getPastLaunchesUseCase.invoke(offset).doOnSuccess {
                _pastLaunches.apply {
                    value?.addAll(it)
                    notifyObservers()
                }
                _currentPage.value = _currentPage.value!! + 1
            }
        }
    }

}
