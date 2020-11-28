package com.dynamo.spacex.data.network

import androidx.annotation.WorkerThread
import com.dynamo.spacex.data.model.pastlaunch.Launch
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 * This Service will indicate the API calls related to launches of the SpaceX
 **/
interface LaunchesService {

    @WorkerThread
    @GET("launches/past")
    suspend fun getPastLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): List<Launch>

}
