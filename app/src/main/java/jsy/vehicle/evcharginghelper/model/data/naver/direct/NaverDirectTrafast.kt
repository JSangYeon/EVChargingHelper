package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NaverDirectTrafast(
    @SerializedName("trafast")
    @Expose
    val trafast: ArrayList<NaverDirectRouteData>,
)


