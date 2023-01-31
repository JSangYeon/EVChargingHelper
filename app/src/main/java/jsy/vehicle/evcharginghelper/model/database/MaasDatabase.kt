package jsy.vehicle.evcharginghelper.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jsy.vehicle.evcharginghelper.model.database.DatabaseConfig.DATABASE_VERSION
import jsy.vehicle.evcharginghelper.model.database.dao.RouteHistoryDao
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory


@Database(entities = [RouteHistory::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(DataListConverters::class)
abstract class MaasDatabase : RoomDatabase() {
    abstract fun routeHistoryDao(): RouteHistoryDao
}