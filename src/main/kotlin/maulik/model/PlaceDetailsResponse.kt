package maulik.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlaceDetailsResponse(
    @SerializedName("html_attributions")
    @Expose
    val htmlAttributions: List<Any>?,
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("result")
    @Expose
    val result: Result?,
    @SerializedName("results")
    @Expose
    val results: List<Result>?,
)

data class Result(
    @SerializedName("address_components")
    @Expose
    val addressComponents: List<AddressComponent>?,
    @SerializedName("adr_address")
    @Expose
    val adrAddress: String?,
    @SerializedName("formatted_address")
    @Expose
    val formattedAddress: String?,
    @SerializedName("geometry")
    @Expose
    val geometry: Geometry?,
    @SerializedName("icon")
    @Expose
    val icon: String?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("place_id")
    @Expose
    val placeId: String?,
    @SerializedName("reference")
    @Expose
    val reference: String?,
    @SerializedName("scope")
    @Expose
    val scope: String?,
    @SerializedName("types")
    @Expose
    val types: List<String>?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("utc_offset")
    @Expose
    val utcOffset: Int?,
    @SerializedName("vicinity")
    @Expose
    val vicinity: String?,
)

data class Geometry(
    @SerializedName("bounds")
    @Expose
    val bounds: Bounds?,
    @SerializedName("location")
    @Expose
    val location: Location?,
    @SerializedName("location_type")
    @Expose
    val locationType: String?,
    @SerializedName("viewport")
    @Expose
    val viewport: Bounds?,
)

data class Bounds(
    @SerializedName("northeast")
    @Expose
    val northEast: Location?,
    @SerializedName("southwest")
    @Expose
    val southWest: Location?,
)

data class Location(
    @SerializedName("lat")
    @Expose
    val latitude: Double?,
    @SerializedName("lng")
    @Expose
    val longitude: Double?,
)

data class AddressComponent(
    @SerializedName("long_name")
    @Expose
    val longName: String?,
    @SerializedName("short_name")
    @Expose
    val shortName: String?,
    @SerializedName("types")
    @Expose
    val types: List<String>?,
)
