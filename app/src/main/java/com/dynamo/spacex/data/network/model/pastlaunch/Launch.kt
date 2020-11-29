package com.dynamo.spacex.data.network.model.pastlaunch

data class Launch(
    val details: String? = null, // Engine failure at 33 seconds and loss of vehicle
    val flight_number: Int? = null, // 1
    val is_tentative: Boolean? = null, // false
    val launch_date_local: String? = null, // 2006-03-25T10:30:00+12:00
    val launch_date_unix: Int? = null, // 1143239400
    val launch_date_utc: String? = null, // 2006-03-24T22:30:00.000Z
    val launch_failure_details: LaunchFailureDetails? = null,
    val launch_site: LaunchSite? = null,
    val launch_success: Boolean = false, // false
    val launch_window: Int? = null, // 0
    val launch_year: String? = null, // 2006
    val links: Links? = null,
    val mission_id: List<Any>? = null,
    val mission_name: String? = null, // FalconSat
    val rocket: Rocket? = null,
    val ships: List<Any>? = null,
    val static_fire_date_unix: Int? = null, // 1142553600
    val static_fire_date_utc: String? = null, // 2006-03-17T00:00:00.000Z
    val tbd: Boolean? = null, // false
    val telemetry: Telemetry? = null,
    val tentative_max_precision: String? = null, // hour
    val timeline: Timeline? = null,
    val upcoming: Boolean? = null // false
)