package com.dynamo.spacex.data.network.model.pastlaunch

data class Rocket(
    val fairings: Fairings,
    val first_stage: FirstStage,
    val rocket_id: String, // falcon1
    val rocket_name: String, // Falcon 1
    val rocket_type: String, // Merlin A
    val second_stage: SecondStage
)