package com.example.eztap.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datalayyer.model.Status
import com.example.datalayyer.model.Uidata
import com.example.datalayyer.repos.RemoteDataSource
import com.example.eztap.utils.NetworkHelper

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val prRepository: RemoteDataSource,
                                                val networkHelper: NetworkHelper) : ViewModel()
{

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Pair<Boolean,String>>(Pair(false,""))
    val isDataLoadingError: LiveData<Pair<Boolean, String>> = _isDataLoadingError

    private val _data = MutableLiveData<List<Uidata>>()
    val data: LiveData<List<Uidata>>
        get() = _data

    init {
        fetchData()
    }

    fun fetchData() {

        viewModelScope.launch {

            _dataLoading.value = true;

            if (networkHelper.isNetworkConnected()) {

                prRepository.fetchCustomUI().let {res->

                    _dataLoading.value  = false;

                    if (res.status == Status.SUCCESS) {

                        _isDataLoadingError.value = Pair(false,"")
                        _data.postValue(res.data?.uidata)

                    } else {

                        _isDataLoadingError.value = Pair(true,"No Match Found")

                    }

                }

            }
            else {

                _dataLoading.value = false;
                _isDataLoadingError.value = Pair(true,"Slow Internet")

            }
        }
    }

}