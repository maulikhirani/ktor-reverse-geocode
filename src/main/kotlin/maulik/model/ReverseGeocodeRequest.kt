package maulik.model

data class ReverseGeocodeRequest(
    val apiKey: String,
    val latitude: Double,
    val longitude: Double
)
