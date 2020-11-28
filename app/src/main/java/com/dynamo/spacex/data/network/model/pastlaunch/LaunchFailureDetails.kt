package com.dynamo.spacex.data.network.model.pastlaunch

data class LaunchFailureDetails(
    val altitude: Any?, // null
    val reason: String, // merlin engine failure
    val time: Int // 33
)