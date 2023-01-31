package jsy.vehicle.evcharginghelper.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.naver.maps.geometry.LatLng
import java.util.*

class DataListConverters {
    @TypeConverter
    fun latLngToJson(value: LatLng): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToLatLng(value: String): LatLng {
        return Gson().fromJson(value, LatLng::class.java)
    }

    @TypeConverter
    fun latLngListToJson(value: ArrayList<LatLng>): String {
//        Log.d("DateListConverters", "latLngLIstToJson : ${value}}");
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToLatLngList(value: String): ArrayList<LatLng> {
//        Log.d("jsonToLatLngLIst", "value : $value")
//        val a =  Gson().fromJson(value, Array<LatLng>::class.java).toCollection(ArrayList<LatLng>())

//        Log.d("jsonToLatLngLIst", "a : $a")

        return Gson().fromJson(value, Array<LatLng>::class.java).toCollection(ArrayList<LatLng>())
    }
}