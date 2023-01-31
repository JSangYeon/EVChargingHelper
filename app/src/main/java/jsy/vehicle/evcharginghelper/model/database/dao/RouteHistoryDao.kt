package jsy.vehicle.evcharginghelper.model.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory

@Dao
interface RouteHistoryDao {
    @Query("SELECT * FROM routeHistory ORDER BY uid DESC limit 10")
    fun readAllPathHistory(): Flowable<Array<RouteHistory>>

    @Query("SELECT * from routeHistory where departure_place_name like :departurePlaceName and destination_name like :destinationName ORDER BY uid DESC limit 10")
    fun readPathHistoryByName(
        departurePlaceName: String,
        destinationName: String
    ): Flowable<Array<RouteHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPathHistory(vararg data: RouteHistory): Completable

    @Delete
    fun deletePathHistories(vararg data: RouteHistory): Completable
}