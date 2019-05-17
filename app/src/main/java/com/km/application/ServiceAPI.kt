package com.km.application

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {
    @GET("users/{login}")
    fun userInfo(@Path("login") name: String): Observable<NetworkObject.UserInfo>

    @GET("users/{login}/repos")
    fun userRepo(@Path("login") name: String) : Observable<List<NetworkObject.Repo>>
}