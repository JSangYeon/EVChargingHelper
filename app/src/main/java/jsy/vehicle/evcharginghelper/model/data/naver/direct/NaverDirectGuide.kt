package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NaverDirectGuide(

    @SerializedName("pointIndex")
    @Expose
    val pointIndex: Int,

    @SerializedName("type")
    @Expose
    val type: Int,

    @SerializedName("instructions")
    @Expose
    val instructions: String,

    @SerializedName("distance")
    @Expose
    val distance: Int,

    @SerializedName("duration")
    @Expose
    val duration: Int,

)