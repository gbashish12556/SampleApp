package com.example.datalayyer.repos

import android.app.UiAutomation
import com.example.datalayyer.model.UiResponse
import com.example.datalayyer.model.Uidata
import com.example.datalayyer.source.ApiHelper
import retrofit2.Response

class RemoteResponseImpl(val apiHelper: ApiHelper) : RemoteDataSource {
    override suspend fun getAllResponse(): Response<UiResponse> {
       return apiHelper.getAllResponse()
    }
}