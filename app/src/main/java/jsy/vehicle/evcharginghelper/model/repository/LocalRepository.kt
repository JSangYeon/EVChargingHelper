package jsy.vehicle.evcharginghelper.model.repository

import jsy.vehicle.evcharginghelper.model.database.dao.RouteHistoryDao
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val routeHistoryDao: RouteHistoryDao,
) {
    suspend fun getPathListHistory(): Array<RouteHistory> =
        withContext(Dispatchers.IO) {
            routeHistoryDao.readAllPathHistory()
        }

    suspend fun addPathHistory(vararg history: RouteHistory) =
        withContext(Dispatchers.IO) {
            routeHistoryDao.addPathHistory(*history)
        }

}
