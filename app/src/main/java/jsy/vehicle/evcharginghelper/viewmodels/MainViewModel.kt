package jsy.vehicle.evcharginghelper.viewmodels

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.vehicle.evcharginghelper.base.BaseViewModel
import jsy.vehicle.evcharginghelper.base.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel() {

    private val _mainText = SingleLiveEvent<String>()
    val mainText: LiveData<String>
        get() = _mainText


    private val _clickedIndex = SingleLiveEvent<Int>()
    val clickedIndex: LiveData<Int>
        get() = _clickedIndex


    init {
        _mainText.value = "1234"
    }




    fun changeMainText() {

//        _mainText.value = distanceCheck().toString()

//        _mainText.value = MvvmSampleApplication.instance.getString(R.string.test_provider)
    }

//    private fun distanceCheck() : Float {
//
//        val test = DistanceCalculator()
//
//        val latitude = 37.5666805
//        val longitude = 126.9784147
//
//        val startLocation = Location("startLocation")
//        startLocation.latitude = latitude
//        startLocation.longitude = longitude
//
//        val locationList = ArrayList<Location>()
//
//
//        for (i in 0..5) {
//
//            val location = Location("location$i")
//            location.latitude = latitude + ((i + 1) * 0.002)
//            location.longitude = longitude - ((i + 1) * 0.002)
//
//            locationList.add(location)
//        }
//
//        val minDistance = test.shortestDistance(startLocation, locationList)
//
//        Log.d("test", "minDistance : $minDistance")
//
//        return minDistance
//    }
}