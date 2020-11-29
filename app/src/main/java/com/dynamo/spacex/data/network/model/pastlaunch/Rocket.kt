package com.dynamo.spacex.data.network.model.pastlaunch

data class Rocket(
    val fairings: Fairings? = null,
    val first_stage: FirstStage? = null,
    val rocket_id: String? = null, // falcon1
    val rocket_name: String? = null, // Falcon 1
    val rocket_type: String? = null, // Merlin A
    val second_stage: SecondStage? = null
)