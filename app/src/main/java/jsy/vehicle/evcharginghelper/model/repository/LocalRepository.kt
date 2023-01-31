package jsy.vehicle.evcharginghelper.model.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jsy.vehicle.evcharginghelper.model.database.dao.RouteHistoryDao
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val routeHistoryDao: RouteHistoryDao
) {

    fun getPathListHistory(): Flowable<Array<RouteHistory>> =
        routeHistoryDao.readAllPathHistory().subscribeOn(Schedulers.io())

    fun addPathHistory(vararg history: RouteHistory): Completable =
        routeHistoryDao.addPathHistory(*history).subscribeOn(Schedulers.io())
//    fun addPlaceHistory(place: PlaceSearchResult.Place) =
//        historyDataSource.addPlaceHistory(place.createPlaceHistory())
//
//    fun readAllPlaceHistory(): Maybe<Array<PlaceHistory>> =
//        historyDataSource.getPlaceHistory()
}
