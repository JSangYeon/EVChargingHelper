package jsy.vehicle.evcharginghelper.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.naver.maps.geometry.LatLng
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.DEPARTURE_PLACE_LAT_LNG
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.DEPARTURE_PLACE_NAME
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.DESTINATION_NAME
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.DESTINATION_NAME_LAT_LNG
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.PATH

@Entity(indices = [Index(value = [DEPARTURE_PLACE_NAME, DESTINATION_NAME, PATH], unique = true)])
data class RouteHistory(

    @ColumnInfo(name = DEPARTURE_PLACE_NAME)
    val departurePlaceName: String,

    @ColumnInfo(name = DESTINATION_NAME)
    val destinationName: String,

    @ColumnInfo(name = DEPARTURE_PLACE_LAT_LNG)
    val departurePlaceLatLng: LatLng,

    @ColumnInfo(name = DESTINATION_NAME_LAT_LNG)
    val destinationLatLng: LatLng,

    @ColumnInfo(name = PATH)
    val path: ArrayList<LatLng>,

    ) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

//fun RouteHistory.createPlace(): RouteSearchResult.Place =
//    RouteSearchResult.Place(name, lat, lng, placeType).apply {
//        code = this@createPlace.code
//    }