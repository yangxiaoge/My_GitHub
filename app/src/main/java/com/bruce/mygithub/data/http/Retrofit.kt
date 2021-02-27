package com.bruce.mygithub.data.http

import android.util.Log
import com.bruce.mygithub.BuildConfig
import com.bruce.mygithub.user.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 网络封装
 * </pre>
 */

private const val BASE_URL = "https://api.github.com/"
private const val TIMEOUT = 60L
private const val TAG = "Retrofit"

val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.e(TAG, message)
    }
}).also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(OAuthInterceptor())
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .also {
        if (BuildConfig.DEBUG) {
            //debug模式输出网络请求日志
            it.addInterceptor(httpLoggingInterceptor)
        }
    }.build()

val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//接口代理实现
object UserService : UserApi by retrofit.create(UserApi::class.java)
