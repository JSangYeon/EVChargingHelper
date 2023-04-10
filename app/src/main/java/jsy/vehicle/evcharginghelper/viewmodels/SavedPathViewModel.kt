package jsy.vehicle.evcharginghelper.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseViewModel
import jsy.vehicle.evcharginghelper.base.SingleLiveEvent
import jsy.vehicle.evcharginghelper.model.repository.LocalRepository
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedPathViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _pathList = SingleLiveEvent<Array<RouteHistory>>()

    val pathList: LiveData<Array<RouteHistory>> = _pathList


    init {
        getAllPlace()
    }

    fun navigateNaverMapFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_saved_path_to_naver_map)
    }

    private fun getAllPlace() {
        CoroutineScope(Dispatchers.IO).launch {
            localRepository.getPathListHistory().let  { pathList ->
                Log.d(logTag, "getPlaceSize : ${pathList.size}")
                pathList.forEach { path->
                    Log.d(logTag, "getPlace : ${path}")
                }
                _pathList.postValue(pathList)
            }
        }

    }


}