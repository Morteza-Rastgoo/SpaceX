package com.dynamo.spacex.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
open class BaseViewModel @Inject constructor() : ViewModel() {

    /**
     * Holds the state of the views. This can be used in most of the cases in a page that load data
     * from network. You can update your views in fragment or activity based on this live data.
     */
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: MutableLiveData<ViewState>
        get() = _viewState


    /**
     * Handles the threading of the coroutine in IO Dispatcher. Also changes the view state to show
     * and hide the loading and show error if necessary.
     */
    fun launchDataLoad(loadingState: ViewState = ViewState.LOADING, block: suspend () -> Any) {
        viewModelScope.launch {
            // TODO: 29/11/2020 AD Handle No internet condition
            viewState.value = loadingState
            try {
                //Do the heavy work in background thread
                withContext(Dispatchers.IO) {
                    block()
                }
                viewState.value = ViewState.SHOW_DATA
            } catch (e: Exception) {
                viewState.value = ViewState.GENERAL_ERROR
            }
        }
    }

}

/**
 * Common states of the view when the page loads data from network or similar situations.
 */
enum class ViewState {
    LOADING, LOAD_MORE, GENERAL_ERROR, NO_INTERNET, SHOW_DATA
}