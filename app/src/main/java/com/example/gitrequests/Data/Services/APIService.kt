package com.example.gitrequests.Data.Services

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.gitrequests.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    private const val BASE_URL = "https://api.github.com/"
    val retrofit: Retrofit
    private const val authToken = BuildConfig.GITHUB_ACCESS_TOKEN

    private val authInterceptor = AuthInterceptor(authToken)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}