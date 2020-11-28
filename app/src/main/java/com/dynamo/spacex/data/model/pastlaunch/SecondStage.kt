package com.dynamo.spacex.data.model.pastlaunch

data class SecondStage(
    val block: Int, // 1
    val payloads: List<Payload>
)