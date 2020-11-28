package com.dynamo.spacex.data.model.pastlaunch

data class Launch(
    val details: String, // Engine failure at 33 seconds and loss of vehicle
    val flight_number: Int, // 1
    val is_tentative: Boolean, // false
    val launch_date_local: String, // 2006-03-25T10:30:00+12:00
    val launch_date_unix: Int, // 1143239400
    val launch_date_utc: String, // 2006-03-24T22:30:00.000Z
    val launch_failure_details: LaunchFailureDetails,
    val launch_site: LaunchSite,
    val launch_success: Boolean, // false
    val launch_window: Int, // 0
    val launch_year: String, // 2006
    val links: Links,
    val mission_id: List<Any>,
    val mission_name: String, // FalconSat
    val rocket: Rocket,
    val ships: List<Any>,
    val static_fire_date_unix: Int, // 1142553600
    val static_fire_date_utc: String, // 2006-03-17T00:00:00.000Z
    val tbd: Boolean, // false
    val telemetry: Telemetry,
    val tentative_max_precision: String, // hour
    val timeline: Timeline,
    val upcoming: Boolean // false
)