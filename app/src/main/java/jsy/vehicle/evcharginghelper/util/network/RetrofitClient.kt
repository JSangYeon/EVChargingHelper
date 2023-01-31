package jsy.vehicle.evcharginghelper.util.network

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import com.naver.maps.geometry.LatLng
import jsy.vehicle.evcharginghelper.model.data.naver.direct.deserializer.LatLngDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.collections.ArrayList


object RetrofitClient {

    val TYPE_WIFI = 1
    val TYPE_MOBILE = 2
    val TYPE_NOT_CONNECTED = 0
    val NETWORK_STATUS_NOT_CONNECTED = 0
    val NETWORK_STAUS_WIFI = 1
    val NETWORK_STATUS_MOBILE = 2

    fun retrofit(baseUrl: String = ApiConfig.PUBLIC_DATA_API_URL): Retrofit {

        val builder = GsonBuilder()
//        builder.registerTypeAdapter(Array<LatLng>::class.java, LatLngListDeserializer())
        builder.registerTypeAdapter(LatLng::class.java, LatLngDeserializer())
        val gson = builder.create()

        val client = getOkHttpClient().build()
        return Retrofit.Builder()
//            .client(getOkHttpClient().build())
            .client(getUnsafeOkHttpClient().build())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Retrofit SSL 우회 접속 통신
     */
    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { hostname, session -> true }
    }

    private fun getOkHttpClient() = OkHttpClient
        .Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//                connectionSpecs(getSSL())
//            }
            addInterceptor(Interceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()

                return@Interceptor it.proceed(request)
            })
        }
}