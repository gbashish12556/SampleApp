package com.example.eztap.ui

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHost
import com.example.datalayyer.model.Uidata
import androidx.compose.ui.Modifier
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MainActivity();

        }
    }

    @Composable
    fun MainActivity() {

        var uiDataList = viewModel.data.observeAsState()

        var errorState = viewModel.isDataLoadingError.observeAsState()

        var loadingState =  viewModel.dataLoading.observeAsState()

        LazyColumn(

            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)

        ) {

            item {

                if(errorState.value!!.first) {

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = errorState.value!!.second
                        )
                    }

                }

                if(loadingState.value!!) {

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Loading...."
                        )
                    }

                }

            }

            items(uiDataList.value!!) { data ->
               ListItem(uiData = data)
            }

        }
    }


    @Composable
    fun ListItem(uiData:Uidata){

    }
}