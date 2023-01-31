package jsy.vehicle.evcharginghelper.model.api

import io.reactivex.Flowable
import jsy.vehicle.evcharginghelper.BuildConfig
import jsy.vehicle.evcharginghelper.model.data.naver.direct.NaverDirectResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverDirectApi {

    @GET("/map-direction/v1/driving")
    fun getMapDirection(
        @Header("X-NCP-APIGW-API-KEY-ID") naver_map_client_id: String= BuildConfig.NAVER_MAP_CLIENT_ID,
        @Header("X-NCP-APIGW-API-KEY") naver_map_client_secret: String=BuildConfig.NAVER_MAP_CLIENT_SECRET,
        @Query("start", encoded = true) start: String, // "(Double) lng, (Double) lat"
        @Query("goal", encoded = true) goal: String, // "(Double) lng, (Double) lat"
        @Query("option") option: String = "trafast",

        ): Flowable<Response<NaverDirectResponse>>

}