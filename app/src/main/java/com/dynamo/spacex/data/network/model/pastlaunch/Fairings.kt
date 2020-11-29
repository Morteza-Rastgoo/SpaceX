package com.dynamo.spacex.data.network.model.pastlaunch

data class Fairings(
    val recovered: Boolean, // false
    val recovery_attempt: Boolean, // false
    val reused: Boolean, // false
    val ship: Any? // null
)