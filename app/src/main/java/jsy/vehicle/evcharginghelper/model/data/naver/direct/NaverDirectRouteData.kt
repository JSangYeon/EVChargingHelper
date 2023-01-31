package jsy.vehicle.evcharginghelper.model.data.naver.direct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.naver.maps.geometry.LatLng

data class NaverDirectRouteData (

    @SerializedName("summary")
    @Expose
    val summary: NaverDirectSummary,

    @SerializedName("path")
    @Expose
    val path: Array<LatLng>,

    @SerializedName("section")
    @Expose
    val section: ArrayList<NaverDirectSection>,

    @SerializedName("guide")
    @Expose
    val guide: ArrayList<NaverDirectGuide>,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NaverDirectRouteData

        if (summary != other.summary) return false
        if (!path.contentEquals(other.path)) return false
        if (section != other.section) return false
        if (guide != other.guide) return false

        return true
    }

    override fun hashCode(): Int {
        var result = summary.hashCode()
        result = 31 * result + path.contentHashCode()
        result = 31 * result + section.hashCode()
        result = 31 * result + guide.hashCode()
        return result
    }
}
