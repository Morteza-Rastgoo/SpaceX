package com.dynamo.spacex.data.network.model.pastlaunch

data class Launch(
    val details: String? = null, // Engine failure at 33 seconds and loss of vehicle
    val flight_number: Int? = null, // 1
    val launch_date_utc: String? = null, // 2006-03-24T22:30:00.000Z
    val launch_success: Boolean = false, // false
    val launch_site: LaunchSite? = null,
    val launch_year: String? = null, // 2006
    val mission_name: String? = null, // FalconSat
    val rocket: Rocket? = null,
    val links: Links? = null,
)