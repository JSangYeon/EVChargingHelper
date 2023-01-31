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
import jsy.vehicle.evcharginghelper.model.repository.EVCSRepository
import jsy.vehicle.evcharginghelper.model.repository.LocalRepository
import jsy.vehicle.evcharginghelper.model.repository.NaverDirectRepository
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory
import jsy.vehicle.evcharginghelper.util.noti.SampleNotificationUtils
import javax.inject.Inject


@HiltViewModel
class NaverMapViewModel @Inject constructor(
    private val evcsRepository: EVCSRepository,
    private val naverDirectRepository: NaverDirectRepository,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _markerList = SingleLiveEvent<ArrayList<Marker>>()
    private val _routePath = SingleLiveEvent<PathOverlay>()
    private val _isProgress = SingleLiveEvent<Boolean>()
    private val _currentLocation = SingleLiveEvent<LatLng>()

    val markerList: LiveData<ArrayList<Marker>> = _markerList
    val routePath: LiveData<PathOverlay> = _routePath
    val isProgress: LiveData<Boolean> = _isProgress
    val currentLocation: LiveData<LatLng> = _currentLocation


    fun navigateSecondFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_naver_map_to_saved_path)
    }

    fun getEVCS() {

        Log.d(logTag, "testRetrofit")
        evcsRepository.getVehicleLocation().subscribe({ response ->

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
        }, {
            Log.d(logTag, "retrofit error getEVCS : $it")
        }).let { }
    }

    fun setRouteByRouteHistory(routeHistory: RouteHistory) {
        val latlngList = routeHistory.path
        val path = PathOverlay()
        path.coords = latlngList
        _routePath.value = path
    }

    fun getRoute(poistionName: String, markerPosition: LatLng) {
        disposables.clear()
        if (_currentLocation.value != null) {
            _isProgress.value = true
            if (_routePath.value != null) _routePath.value!!.map = null
            naverDirectRepository.getNaverDirect(_currentLocation.value!!, markerPosition)
                .subscribe({ response ->
//                    Log.d(logTag, "response body : ${response.body()}")
                    val naverMapDirectResponse = response.body()

                    if (naverMapDirectResponse != null &&
                        naverMapDirectResponse.route != null &&
                        naverMapDirectResponse.route.trafast != null &&
                        naverMapDirectResponse.route.trafast.isNotEmpty() &&
                        naverMapDirectResponse.route.trafast[0].path != null
                    ) {
                        val latlngList =
                            naverMapDirectResponse.route.trafast[0].path.toCollection(ArrayList())
                        val path = PathOverlay()
                        path.coords = latlngList
                        _routePath.postValue(path)


                        val json = Gson().toJson(latlngList)

                        Log.d(logTag, "test Json : $json")
                        localRepository.addPathHistory(
                            RouteHistory(
                                departurePlaceName = "목동",
                                destinationName = poistionName,
                                departurePlaceLatLng = _currentLocation.value!!,
                                destinationLatLng = markerPosition,
                                path = latlngList
                            )
                        ).subscribe()
                    }
                    _isProgress.postValue(false)
                }, {
                    _isProgress.postValue(false)
                    Log.d(logTag, "retrofit error getRoute : $it")
                }).let { disposable ->
                    disposables.add(disposable)
                }
        }
    }

    fun setLocationMokdong() {
        _currentLocation.value = LatLng(37.5261, 126.8643)
    }

    fun getAllPlace() {

//        private fun initNoti(){
        val sampleNotificationUtils = SampleNotificationUtils()
        sampleNotificationUtils.useNoti()
//        }
//        var a = ""
//        val temp = localRepository.getPathListHistory().subscribe(
//            { placeList ->
//                Log.d(logTag, "getPlaceSize : ${placeList.size}")
//                placeList.forEach { place->
//
//                    Log.d(logTag, "getPlace : ${place}")
//
//                    a = place.destinationName
//                }
//            },{
//
//                Log.d(logTag, "getAllPlace error : $it")
//            }
//        );
    }
}