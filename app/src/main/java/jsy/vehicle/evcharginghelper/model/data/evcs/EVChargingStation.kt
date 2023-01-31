package jsy.vehicle.evcharginghelper.model.data.evcs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EVChargingStation(

    @SerializedName("statNm")
    @Expose
    val statNm: String,

    @SerializedName("statId")
    @Expose
    val statId: String,

    @SerializedName("chgerId")
    @Expose
    val chgerId: String,

    @SerializedName("chgerType")
    @Expose
    val chgerType: String,

    @SerializedName("addr")
    @Expose
    val addr: String,

    @SerializedName("location")
    @Expose
    val location: String?,

    @SerializedName("lat")
    @Expose
    val lat: String,

    @SerializedName("lng")
    @Expose
    val lng: String,

    @SerializedName("busiId")
    @Expose
    val busiId: String,

    @SerializedName("stat")
    @Expose
    val stat: String,

    @SerializedName("statUpdDt")
    @Expose
    val statUpdDt: String,

    @SerializedName("lastTsdt")
    @Expose
    val lastTsdt: String,

    @SerializedName("lastTedt")
    @Expose
    val lastTedt: String,

    @SerializedName("nowTsdt")
    @Expose
    val nowTsdt: String,

    @SerializedName("powerType")
    @Expose
    val powerType: String,

    @SerializedName("output")
    @Expose
    val output: String,

    @SerializedName("method")
    @Expose
    val method: String,

    @SerializedName("zcode")
    @Expose
    val zcode: String,

    @SerializedName("zscode")
    @Expose
    val zscode: String,

    @SerializedName("kind")
    @Expose
    val kind: String,

    @SerializedName("kindDetail")
    @Expose
    val kindDetail: String,


    @SerializedName("parkingFree")
    @Expose
    val parkingFree: String,

    @SerializedName("note")
    @Expose
    val note: String,


    @SerializedName("limitYn")
    @Expose
    val limitYn: String,

    @SerializedName("limitDetail")
    @Expose
    val limitDetail: String,

    @SerializedName("delYn")
    @Expose
    val delYn: String,

    @SerializedName("delDetail")
    @Expose
    val delDetail: String,

    @SerializedName("trafficYn")
    @Expose
    val trafficYn: String

    )