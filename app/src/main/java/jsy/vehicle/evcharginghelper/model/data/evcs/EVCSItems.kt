package jsy.vehicle.evcharginghelper.model.data.evcs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EVCSItems(

    @SerializedName("item")
    @Expose
    val evChargingStationList: ArrayList<EVChargingStation>,
)
