package jsy.vehicle.evcharginghelper.model.data.naver.direct
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory

import com.naver.maps.geometry.LatLng


data class RouteSearchResult(val searchedKeyword: String = "") {
    var totalItem: ArrayList<Route> = arrayListOf()
    val size: Int
        get() = totalItem.size

    data class Route(
        val departurePlaceName: String,
        val destinationName: String,
        val departurePlaceLatLng: LatLng,
        val destinationLatLng: LatLng,
        val path: ArrayList<LatLng>
    ) {
//        var code: Int = 0
    }
}

fun RouteSearchResult.Route.createPlaceHistory(): RouteHistory {
    return RouteHistory(departurePlaceName, destinationName, departurePlaceLatLng, destinationLatLng, path)
}