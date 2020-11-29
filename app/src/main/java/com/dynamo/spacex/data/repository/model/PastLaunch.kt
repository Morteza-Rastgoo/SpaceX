package com.dynamo.spacex.data.repository.model

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
data  class PastLaunch(
    val flightNumber: Int?,
    val missionName: String?,
    val date: String?,
    val description:String,
    val videoLink: String?,
)