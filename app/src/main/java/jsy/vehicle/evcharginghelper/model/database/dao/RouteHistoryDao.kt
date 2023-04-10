package jsy.vehicle.evcharginghelper.model.database.dao

import androidx.room.*
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory

@Dao
interface RouteHistoryDao {
    @Query("SELECT * FROM routeHistory ORDER BY uid DESC limit 10")
    fun readAllPathHistory(): Array<RouteHistory>

    @Query("SELECT * from routeHistory where departure_place_name like :departurePlaceName and destination_name like :destinationName ORDER BY uid DESC limit 10")
    fun readPathHistoryByName(
        departurePlaceName: String,
        destinationName: String
    ): Array<RouteHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPathHistory(vararg data: RouteHistory)

    @Delete
    suspend fun deletePathHistories(vararg data: RouteHistory)
}