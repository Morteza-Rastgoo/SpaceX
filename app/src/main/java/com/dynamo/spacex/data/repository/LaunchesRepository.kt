package com.dynamo.spacex.data.repository

import com.dynamo.spacex.data.network.LaunchesService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
@Singleton
class LaunchesRepository @Inject constructor(private val launchesService: LaunchesService) {


}