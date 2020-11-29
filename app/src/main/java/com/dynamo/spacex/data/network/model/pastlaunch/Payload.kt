package com.dynamo.spacex.data.network.model.pastlaunch

data class Payload(
    val customers: List<String>,
    val manufacturer: String, // SSTL
    val nationality: String, // United States
    val norad_id: List<Any>,
    val orbit: String, // LEO
    val orbit_params: OrbitParams,
    val payload_id: String, // FalconSAT-2
    val payload_mass_kg: Int, // 20
    val payload_mass_lbs: Int, // 43
    val payload_type: String, // Satellite
    val reused: Boolean // false
)