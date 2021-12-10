package maulik.plugins

import com.way.app.helper.googleApiManager.mapAddressFromGoogle
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import maulik.model.PlaceDetails
import maulik.model.PlaceDetailsResponse
import maulik.model.ReverseGeocodeRequest

const val GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json"

fun Application.configureRouting() {

    install(ContentNegotiation) {
        gson()
    }

    routing {
        post("/reverseGeocode") {
            val request = call.receive<ReverseGeocodeRequest>()
            val placeDetails = getPlaceDetails(request)
            if (placeDetails != null) {
                call.respond(placeDetails)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}

suspend fun getPlaceDetails(request: ReverseGeocodeRequest): PlaceDetails? {
    HttpClient(CIO){
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }.run {
        val response: PlaceDetailsResponse = get(GOOGLE_API_URL) {
            parameter("latlng", "${request.latitude},${request.longitude}")
            parameter("key", request.apiKey)
        }
        return convertResponseToPlaceDetails(response)
    }
}

private fun convertResponseToPlaceDetails(
    placeDetailsResponse: PlaceDetailsResponse
): PlaceDetails? {

    var resultPlace = placeDetailsResponse.result

    if (resultPlace == null && !placeDetailsResponse.results.isNullOrEmpty()) {
        resultPlace = placeDetailsResponse.results[0]
    }

    resultPlace?.addressComponents?.let {
        val placeDetails = mapAddressFromGoogle(
            resultPlace.geometry?.location?.latitude ?: 0.0,
            resultPlace.geometry?.location?.longitude ?: 0.0,
            it
        )
        return if (placeDetails.formattedAddress.isBlank()) null else placeDetails
    }
    return null
}
