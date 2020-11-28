package com.dynamo.spacex.data.repository

import com.dynamo.spacex.data.model.pastlaunch.Launch
import com.dynamo.spacex.data.network.LaunchesService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 * This Repository will load the data related to launches of the SpaceX
 **/
@Singleton
class LaunchesRepository @Inject constructor(private val launchesService: LaunchesService) {


    suspend fun getPastLaunches(
        id: Boolean = false,
        limit: Int = 15,
        offset: Int = 0,
        sort: String? = null,
        order: String = "desc",
    ): List<Launch> {
        return launchesService.getPastLaunches(id, limit, offset, sort, order)
    }
}