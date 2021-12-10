package maulik.model


data class PlaceDetails(
    val latitude: Double,
    val longitude: Double,
    val street: String,
    val addressLine1: String,
    val cityName: String,
    val stateName: String,
    val stateCode: String,
    val countryName: String,
    val countryCode: String,
    val zipCode: String,
    val formattedAddress: String,
)