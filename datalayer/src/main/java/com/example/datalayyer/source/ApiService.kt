package com.example.datalayyer.source

import com.example.datalayyer.model.UiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("/mobileapps/android_assignment.json")
    suspend fun fetchCustomUI(): Response<UiResponse>
}