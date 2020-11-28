package com.dynamo.spacex.data.model.pastlaunch

data class OrbitParams(
    val apoapsis_km: Int, // 500
    val arg_of_pericenter: Any?, // null
    val eccentricity: Any?, // null
    val epoch: Any?, // null
    val inclination_deg: Int, // 39
    val lifespan_years: Any?, // null
    val longitude: Any?, // null
    val mean_anomaly: Any?, // null
    val mean_motion: Any?, // null
    val periapsis_km: Int, // 400
    val period_min: Any?, // null
    val raan: Any?, // null
    val reference_system: String, // geocentric
    val regime: String, // low-earth
    val semi_major_axis_km: Any? // null
)