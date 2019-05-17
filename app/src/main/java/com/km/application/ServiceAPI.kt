package com.km.application

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {
    @GET("users/{name}")
    fun userInfo(@Path("name") name: String): Observable<NetworkObject.UserInfo>

    @GET("users/{name}/repos")
    fun userRepo(@Path("name") name: String) : Observable<List<NetworkObject.Repo>>
}