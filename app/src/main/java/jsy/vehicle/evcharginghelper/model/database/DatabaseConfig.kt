package jsy.vehicle.evcharginghelper.model.database

object DatabaseConfig {
    const val DATABASE_NAME = "maas_database"
    const val DATABASE_VERSION = 1

    //region Entity constants
    const val DEPARTURE_PLACE_NAME = "DEPARTURE_PLACE_NAME" // 출발지명
    const val DESTINATION_NAME = "DESTINATION_NAME" // 목적지명
    const val DEPARTURE_PLACE_LAT_LNG = "DEPARTURE_PLACE_LAT_LNG" // 출발지 좌표
    const val DESTINATION_NAME_LAT_LNG = "DESTINATION_NAME_LAT_LNG" // 목적지 좌표
    const val PATH = "path"
    //endregion Entity constants
}