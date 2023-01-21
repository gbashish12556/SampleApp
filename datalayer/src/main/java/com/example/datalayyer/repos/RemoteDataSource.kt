package com.example.datalayyer.repos

import com.example.datalayyer.model.Resource
import com.example.datalayyer.model.UiResponse
import com.example.datalayyer.model.Uidata
import retrofit2.Response

interface  RemoteDataSource {

    suspend fun fetchCustomUI():Resource<UiResponse>

}