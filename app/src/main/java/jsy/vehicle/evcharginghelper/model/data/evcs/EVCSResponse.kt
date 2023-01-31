package jsy.vehicle.evcharginghelper.model.data.evcs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EVCSResponse( // electric vehicle charging station Response

    @SerializedName("resultMsg")
    @Expose
    val resultMsg: String,


    @SerializedName("totalCount")
    @Expose
    val totalCount: Int,


    @SerializedName("pageNo")
    @Expose
    val pageNo: Int,


    @SerializedName("resultCode")
    @Expose
    val resultCode: String,


    @SerializedName("numOfRows")
    @Expose
    val numOfRows: Int,


    @SerializedName("items")
    @Expose
    val EVCSItems: EVCSItems,
)
