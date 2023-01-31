package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NaverDirectSection(

    @SerializedName("pointIndex")
    @Expose
    val pointIndex: Int,

    @SerializedName("pointCount")
    @Expose
    val pointCount: Int,

    @SerializedName("distance")
    @Expose
    val distance: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("congestion")
    @Expose
    val congestion: Int,

    @SerializedName("speed")
    @Expose
    val speed: Int,

    )
