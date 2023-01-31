package jsy.vehicle.evcharginghelper.model.data.naver.direct.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.naver.maps.geometry.LatLng
import java.lang.reflect.Type


class LatLngDeserializer : JsonDeserializer<LatLng?> {
    val logTag = javaClass.simpleName
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LatLng? {
        if (json == null) return null
        val jsonArray = json.asJsonArray

        return LatLng(jsonArray.get(1).asDouble, jsonArray.get(0).asDouble)
    }

}
