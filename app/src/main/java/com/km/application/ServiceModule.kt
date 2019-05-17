package com.km.application

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object ServiceModule {

    fun restAPI (): ServiceAPI {
        return retrofitInterface().create(ServiceAPI::class.java)
    }

    private val httpClient = OkHttpClient.Builder()
        .addNetworkInterceptor { chain: Interceptor.Chain -> chain.proceed(chain.request().newBuilder().build()) }!!

    private fun retrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient.build())
            .build()
    }
}