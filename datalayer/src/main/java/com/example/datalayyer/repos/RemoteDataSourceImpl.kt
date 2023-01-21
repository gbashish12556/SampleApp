package com.example.datalayyer.repos

import android.app.UiAutomation
import com.example.datalayyer.model.Resource
import com.example.datalayyer.model.UiResponse
import com.example.datalayyer.model.Uidata
import com.example.datalayyer.source.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class RemoteResponseImpl @Inject constructor(val apiHelper: ApiHelper) : RemoteDataSource {
    override suspend fun fetchCustomUI(): Resource<UiResponse> {

        var result =  apiHelper.fetchCustomUI();

        if(result.isSuccessful){

            return Resource.success(result.body())

        }else{

            return Resource.error("No match found", null)

        }

    }
}