package jsy.vehicle.evcharginghelper.model.repository

import com.naver.maps.geometry.LatLng
import jsy.vehicle.evcharginghelper.model.data.naver.direct.NaverDirectResponse
import jsy.vehicle.evcharginghelper.module.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NaverDirectRepository @Inject constructor() {
    private val logTag = javaClass.simpleName

    suspend fun getNaverDirect(
        startLatLng: LatLng,
        goalLatLng: LatLng,
    ): Response<NaverDirectResponse> {
        val start = "${startLatLng.longitude},${startLatLng.latitude}"
        val goal = "${goalLatLng.longitude},${goalLatLng.latitude}"

        /*
        withContext로 async await을 대체 할 수 있다.
        withContext 블록의 마지막 줄의 값이 반환 값이 된다.
        withContext가 끝나기 전까지 해당 코루틴은 일시정지된다.
         */

        return withContext(Dispatchers.IO) {
            RetrofitModule.getNaverDirectRepository().getMapDirection(
                start = start,
                goal = goal
            )
        }
    }


}