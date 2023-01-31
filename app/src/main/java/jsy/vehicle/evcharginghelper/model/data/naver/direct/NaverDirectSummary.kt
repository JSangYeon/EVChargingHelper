package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.naver.maps.geometry.LatLng

data class NaverDirectSummary (

    @SerializedName("start")
    @Expose
    val start: NaverDirectStart,

    @SerializedName("goal")
    @Expose
    val goal: NaverDirectGoal,

    @SerializedName("distance")
    @Expose
    val distance: Int,

    @SerializedName("duration")
    @Expose
    val duration: Int,

    @SerializedName("etaServiceType")
    @Expose
    val etaServiceType: Int,

    @SerializedName("departureTime")
    @Expose
    val departureTime: String,

    @SerializedName("bbox")
    @Expose
    val bBox: ArrayList<LatLng>,

    @SerializedName("tollFare")
    @Expose
    val tollFare: Int,

    @SerializedName("taxiFare")
    @Expose
    val taxiFare: Int,

    @SerializedName("fuelPrice")
    @Expose
    val fuelPrice: Int,


    )

data class NaverDirectStart (

    @SerializedName("location")
    @Expose
    val location: LatLng,
)

data class NaverDirectGoal (

    @SerializedName("location")
    @Expose
    val location: LatLng,
)




