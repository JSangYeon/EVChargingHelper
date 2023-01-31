package jsy.vehicle.evcharginghelper.model.repository

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jsy.vehicle.evcharginghelper.BuildConfig
import jsy.vehicle.evcharginghelper.model.data.evcs.EVCSResponse
import jsy.vehicle.evcharginghelper.module.RetrofitModule
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EVCSRepository @Inject constructor() {
    private val logTag = javaClass.simpleName

//    fun getDefault(): Flowable<Response<ArrayList<User>>> {
//        Log.d(logTag, "getDefault")
//        return TestRetrofitModule.getService().getDefault().subscribeOn(Schedulers.io())
//    }

     fun getVehicleLocation(): Flowable<Response<EVCSResponse>> {
        return RetrofitModule.getEVSCRepository().getVehicleLocation(
            serviceKey = BuildConfig.EVCS_API_KEY,
        ).subscribeOn(Schedulers.io())
    }
}