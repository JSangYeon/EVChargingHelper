package jsy.vehicle.evcharginghelper.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseViewModel
import jsy.vehicle.evcharginghelper.base.SingleLiveEvent
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory
import jsy.vehicle.evcharginghelper.model.repository.EVCSRepository
import jsy.vehicle.evcharginghelper.model.repository.LocalRepository
import jsy.vehicle.evcharginghelper.model.repository.NaverDirectRepository
import jsy.vehicle.evcharginghelper.util.noti.SampleNotificationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NaverMapViewModel @Inject constructor(
    private val evcsRepository: EVCSRepository,
    private val naverDirectRepository: NaverDirectRepository,
    private val localRepository: LocalRepository,
) : BaseViewModel() {

    private val _markerList = SingleLiveEvent<ArrayList<Marker>>()
    private val _routePath = SingleLiveEvent<PathOverlay>()
    private val _isProgress = SingleLiveEvent<Boolean>()
    private val _currentLocation = SingleLiveEvent<LatLng>()

    val markerList: LiveData<ArrayList<Marker>> = _markerList
    val routePath: LiveData<PathOverlay> = _routePath
    val isProgress: LiveData<Boolean> = _isProgress
    val currentLocation: LiveData<LatLng> = _currentLocation


//    fun navigateSecondFragment(view: View) {
//        Navigation.findNavController(view).navigate(R.id.action_naver_map_to_saved_path)
//    }

    fun getEVCS() {

        Log.d(logTag, "testRetrofit")

        CoroutineScope(Dispatchers.IO).launch {
            evcsRepository.getVehicleLocation().let { response ->
                val evcsResponse = response.body()
                Log.d(logTag, "retrofit 충전소 :  ${evcsResponse}}")
                val markerList = ArrayList<Marker>()
                evcsResponse?.EVCSItems?.evChargingStationList?.forEach { evChargingStation ->
                    Log.d(logTag, "evChargingStation Name : ${evChargingStation.statNm}")
                    val lat = evChargingStation.lat.toDoubleOrNull()
                    val lng = evChargingStation.lng.toDoubleOrNull()
                    if (lat != null && lng != null) {
                        markerList.add(
                            Marker(
                                LatLng(lat, lng)
                            ).apply {
                                tag = evChargingStation.statNm
                                captionText = evChargingStation.statNm
                            }
                        )
                    }
                }
                _markerList.postValue(markerList)
            }
        }.let {

        }
    }

    fun setRouteByRouteHistory(routeHistory: RouteHistory) {
        val latlngList = routeHistory.path
        val path = PathOverlay()
        path.coords = latlngList
        _routePath.value = path
    }

    fun getRoute(poistionName: String, markerPosition: LatLng) {
        if (_currentLocation.value != null) {
            _isProgress.value = true
            if (_routePath.value != null) _routePath.value!!.map = null


            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.d(logTag, "코루틴 스코프 시작 ")
                    val response = naverDirectRepository.getNaverDirect(
                        _currentLocation.value!!,
                        markerPosition
                    )
                    val naverMapDirectResponse = response.body()
                    if (naverMapDirectResponse != null && naverMapDirectResponse.route.trafast.isNotEmpty()
                    ) {
                        val latlngList =
                            naverMapDirectResponse.route.trafast[0].path.toCollection(ArrayList())
                        val path = PathOverlay()
                        path.coords = latlngList
                        _routePath.postValue(path)

                        val json = Gson().toJson(latlngList)

                        localRepository.addPathHistory(
                            RouteHistory(
                                departurePlaceName = "목동",
                                destinationName = poistionName,
                                departurePlaceLatLng = _currentLocation.value!!,
                                destinationLatLng = markerPosition,
                                path = latlngList
                            )
                        )
                    }
                } catch (e: Exception) {
                    Log.d(logTag, "코루틴 스코프 에러 $e")
                    e.printStackTrace()
                } finally {
                    Log.d(logTag, "코루틴 스코프 종료 ")
                    _isProgress.postValue(false) // 프로그레스바 종료
                }

            }.let {
                it
                //코루틴 스코프는 Job을 반환함
            }


            //같은 Dispatchers.Io를 바라보고 있더라도 다른 스코프에 대해서는 독립적임 비동기
//            CoroutineScope(Dispatchers.IO).launch {
//                Log.d("코루틴 실행 테스트","코루틴 스코프")
//            }

        }



    }


    fun setLocationMokdong() {
        _currentLocation.value = LatLng(37.5261, 126.8643)
    }

//    fun getAllPlace() {
//
//        val sampleNotificationUtils = SampleNotificationUtils()
//        sampleNotificationUtils.useNoti()
//
//    }
}