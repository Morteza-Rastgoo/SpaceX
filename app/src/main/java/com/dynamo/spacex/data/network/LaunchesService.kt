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

    /**
     * Call to show the Past Launches
     *  Optional Output Control QueryStrings
     *         @param 	Sample	        Type	    Description
     *  @param id 	    true	        boolean	    Set as true to show mongo document id's
     *  @param limit 	3	            integer	    Limit results returned, defaults to all documents returned
     *  @param offset 	3	            integer	    Offset or skip results from the beginning of the query
     *  @param sort 	flight_number	string	    Change result sorting by setting value to any parameter in this list
     *  @param order 	desc	        string	    Change result ordering by setting values of asc or desc
     */
    @WorkerThread
    @GET("launches/past")
    suspend fun getPastLaunches(
        @Query("id") id: Boolean = false,
        @Query("limit") limit: Int = 15,
        @Query("offset") offset: Int = 0,
        @Query("sort") sort: String? = null,
        @Query("order") order: String = "desc",
    ): List<Launch>

}
