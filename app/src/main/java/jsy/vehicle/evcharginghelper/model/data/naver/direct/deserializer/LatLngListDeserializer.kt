package jsy.vehicle.evcharginghelper.model.data.naver.direct.deserializer

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.naver.maps.geometry.LatLng
import java.lang.reflect.Type


class LatLngListDeserializer : JsonDeserializer<Array<LatLng>?> {
    val logTag = javaClass.simpleName
        override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Array<LatLng>? {
        val list = ArrayList<LatLng>()
        Log.d(logTag, "LatLngListDeserializer start")

        if(json==null) return null


        val jsonArray = json.asJsonArray
        Log.d(logTag, "jsonArray : $jsonArray")
        jsonArray.forEach { jsonElement ->
            val doubleList = jsonElement.asJsonArray
            val latLng = LatLng(doubleList.get(1).asDouble, doubleList.get(0).asDouble)
            list.add(latLng)
        }

            Log.d(logTag, "list : $list")
        return list.toTypedArray()
    }

}
