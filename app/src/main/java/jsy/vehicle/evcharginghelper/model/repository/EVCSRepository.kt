package jsy.vehicle.evcharginghelper.model.repository

import jsy.vehicle.evcharginghelper.BuildConfig
import jsy.vehicle.evcharginghelper.model.data.evcs.EVCSResponse
import jsy.vehicle.evcharginghelper.module.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EVCSRepository @Inject constructor() {
    private val logTag = javaClass.simpleName

    suspend fun getVehicleLocation(): Response<EVCSResponse> {
        return withContext(Dispatchers.IO) {
            RetrofitModule.getEVSCRepository().getVehicleLocation(
                serviceKey = BuildConfig.EVCS_API_KEY,
            )
        }
    }
}