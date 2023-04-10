package jsy.vehicle.evcharginghelper.util.common

import android.content.Context
import jsy.vehicle.evcharginghelper.EVChargingHelperApplication.Companion.getGlobalApplicationContext


fun Int.pixelToDp(): Int {
    val scale = getGlobalApplicationContext().resources.displayMetrics.density
    return (this / scale).toInt()
}

fun Int.dpToPixel(): Int {
    val scale = getGlobalApplicationContext().resources.displayMetrics.density
    return (this * scale).toInt()
}

