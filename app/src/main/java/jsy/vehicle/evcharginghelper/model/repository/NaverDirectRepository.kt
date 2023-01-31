package jsy.vehicle.evcharginghelper.model.repository

import com.naver.maps.geometry.LatLng
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jsy.vehicle.evcharginghelper.model.data.naver.direct.NaverDirectResponse
import jsy.vehicle.evcharginghelper.module.RetrofitModule
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NaverDirectRepository @Inject constructor() {
    private val logTag = javaClass.simpleName

    fun getNaverDirect(
        startLatLng: LatLng,
        goalLatLng: LatLng
    ): Flowable<Response<NaverDirectResponse>> {
        val start = "${startLatLng.longitude},${startLatLng.latitude}"
        val goal = "${goalLatLng.longitude},${goalLatLng.latitude}"
        return RetrofitModule.getNaverDirectRepository().getMapDirection(
            start = start,
            goal = goal
        ).subscribeOn(Schedulers.io())
    }
}