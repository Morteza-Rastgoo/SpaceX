package com.dynamo.spacex.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.data.usecase.launches.GetPastLaunchesUseCase
import com.dynamo.spacex.ui.base.BaseViewModel
import com.dynamo.spacex.ui.base.ViewState
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(private val getPastLaunchesUseCase: GetPastLaunchesUseCase) :
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
    suspend fun getPastLaunches() {
        val offset = currentPage * dataLimit
        launchDataLoad(if (currentPage == 0) ViewState.LOADING else ViewState.LOAD_MORE) {
            getPastLaunchesUseCase.invoke(offset).doOnSuccess {
                _pastLaunches.value = it
                _currentPage++
            }
        }
    }

}

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
inline fun <reified T> T.updateOnSuccess(liveData: MutableLiveData<T>): T {
    liveData.value = this
    return this
}

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
inline fun <reified T> T.doOnSuccess(callback: (T) -> Unit): T {
    callback.invoke(this)
    return this
}