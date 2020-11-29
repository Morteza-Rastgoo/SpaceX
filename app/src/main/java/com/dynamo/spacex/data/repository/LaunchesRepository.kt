package com.dynamo.spacex.data.repository

import com.dynamo.spacex.data.network.LaunchesService
import com.dynamo.spacex.data.repository.model.PastLaunch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 * This Repository will load the data related to launches of the SpaceX
 **/
@Singleton
class LaunchesRepository @Inject constructor(private val launchesService: LaunchesService) {


    /**
     * Call to show the Past Launches
     *  Optional Output Control QueryStrings
     *         @param 	Sample	        Type	    Description
     *  @param limit    3	            integer	    Limit results returned, defaults to all documents returned
     *  @param offset    3	            integer	    Offset or skip results from the beginning of the query
     *  @return PastLaunch model including mission_name and the date part of launch_date_utc
     */
    suspend fun getPastLaunches(
        limit: Int = 15,
        offset: Int = 0,
    ): List<PastLaunch> {
        return launchesService.getPastLaunches(limit = limit,offset = offset)
            .map {
                PastLaunch(
                    flightNumber = it.flight_number,
                    missionName = it.mission_name,
                    date = it.launch_date_utc,
                    description = "Rocket name: ${it.rocket?.rocket_name}\n" +
                            "Rocket type: ${it.rocket?.rocket_type}\n" +
                            "Launch site: ${it.launch_site?.site_name}\n" +
                            "Year: ${it.launch_year}\n" +
                            "Launch success: " + if (it.launch_success!!) "Yes" else "No",
                    videoLink = it.links?.video_link
                )
            }
    }
}