package com.dynamo.spacex.data.network.model.pastlaunch

data class Core(
    val block: Any?, // null
    val core_serial: String, // Merlin1A
    val flight: Int, // 1
    val gridfins: Boolean, // false
    val land_success: Any?, // null
    val landing_intent: Boolean, // false
    val landing_type: Any?, // null
    val landing_vehicle: Any?, // null
    val legs: Boolean, // false
    val reused: Boolean // false
)