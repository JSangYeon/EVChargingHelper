package jsy.vehicle.evcharginghelper

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class EVChargingHelperApplication : Application() {

    companion object {
        lateinit var instance: EVChargingHelperApplication

        /**
         * singleton 애플리케이션 객체를 얻는다.
         *
         * @return singleton 애플리케이션 객체
         */
        @JvmStatic
        fun getGlobalApplicationContext(): EVChargingHelperApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        NaverMapSdk.getInstance(this).client =
            NaverCloudPlatformClient(BuildConfig.NAVER_MAP_CLIENT_ID)
    }

}