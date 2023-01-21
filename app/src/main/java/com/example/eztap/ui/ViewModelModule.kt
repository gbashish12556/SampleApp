package com.example.eztap.ui

import android.content.Context
import com.example.datalayyer.repos.RemoteDataSource
import com.example.datalayyer.repos.RemoteResponseImpl
import com.example.datalayyer.source.ApiHelper
import com.example.eztap.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//@Module
//@InstallIn(ViewModelModule::class)
//class ViewModelModule {
//
//    @Provides
//    @Singleton
//    fun provideNetworkHelper(@ApplicationContext context:Context): NetworkHelper {
//        return NetworkHelper(context);
//    }
//
//}