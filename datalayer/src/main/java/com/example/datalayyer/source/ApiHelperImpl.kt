package com.example.datalayyer.source

import com.example.datalayyer.model.UiResponse
import retrofit2.Response
import javax.inject.Inject


class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun fetchCustomUI(): Response<UiResponse> {

        return apiService.fetchCustomUI();

    }


}