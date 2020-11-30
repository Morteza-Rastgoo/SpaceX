package com.dynamo.spacex.util.extensions

import androidx.lifecycle.MutableLiveData

/**
 * @author : Morteza Rastgoo
 * @since : 7/6/2020 AD, Sun
 **/

/**
 * Notify mutable live data observer
 */
fun <T> MutableLiveData<T>.notifyObservers(newValue: T? = null) {
    this.value = newValue ?: this.value
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