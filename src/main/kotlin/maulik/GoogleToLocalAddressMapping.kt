package com.way.app.helper.googleApiManager

import maulik.model.AddressComponent
import maulik.model.PlaceDetails

fun mapAddressFromGoogle(
    latitude: Double,
    longitude: Double,
    componentList: List<AddressComponent>
): PlaceDetails {

    var streetNumber: String? = null
    var premise: String? = null
    var route: String? = null
    val subLocalityList = mutableListOf<String>()
    var subLocalityLevelOne: String? = null
    var locality: String? = null
    var postalCode: String? = null
    var country: String? = null
    var countryCode: String? = null
    var state: String? = null
    var stateCode: String? = null

    componentList.forEach { address ->

        val types = address.types
        if (types != null) {
            when {
                "street_number" in types -> streetNumber = address.longName
                "premise" in types -> premise = address.longName
                "route" in types -> route = address.longName
                "locality" in types -> locality = address.longName
                "postal_code" in types -> postalCode = address.longName
                "country" in types -> {
                    country = address.longName
                    countryCode = address.shortName
                }
                "administrative_area_level_1" in types -> {
                    state = address.longName
                    stateCode = address.shortName
                }
                "sublocality" in types -> {
                    if ("sublocality_level_1" in types) {
                        subLocalityLevelOne = address.longName
                    }
                    address.longName?.let { subLocalityList.add(it) }
                }
            }
        }
    }

    val street = if (streetNumber?.isBlank() == true) {
        premise
    } else {
        streetNumber
    }

    val addressLine1List = mutableListOf<String>()
    route?.let { addressLine1List.add(it) }
    addressLine1List.addAll(subLocalityList)

    var cityName = locality

    if (locality.isNullOrBlank() &&
        state?.equals("New York", ignoreCase = true) == true &&
        subLocalityLevelOne?.isBlank() == false
    ) {
        cityName = subLocalityLevelOne
        subLocalityLevelOne?.let {
            addressLine1List.remove(it)
        }
    }

    val addressLine1 = addressLine1List.joinToString()

    return PlaceDetails(
        latitude,
        longitude,
        street ?: "",
        addressLine1,
        cityName ?: "",
        state ?: "",
        stateCode ?: "",
        country ?: "",
        countryCode ?: "",
        postalCode ?: "",
        listOf(
            street,
            addressLine1,
            cityName,
            state,
            postalCode,
            country
        ).filter { !it.isNullOrBlank() }.joinToString()
    )
}
