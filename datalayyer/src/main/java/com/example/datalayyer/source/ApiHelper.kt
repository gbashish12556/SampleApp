package com.example.datalayyer.source

import com.example.datalayyer.model.UiResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getAllResponse(
        orgname: String?,
        repoName: String?,
        state: String?
    ): Response<UiResponse>

}