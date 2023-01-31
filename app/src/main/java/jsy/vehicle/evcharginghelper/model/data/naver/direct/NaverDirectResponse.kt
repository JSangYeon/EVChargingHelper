package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NaverDirectResponse(

    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("message")
    @Expose
    val message: String,

    @SerializedName("currentDateTime")
    @Expose
    val currentDateTime: String,

    @SerializedName("route")
    @Expose
    val route: NaverDirectTrafast,


    )