package com.example.eztap.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.datalayyer.model.Uidata
import com.example.eztap.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.HashMap


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels();

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


        Box(modifier = Modifier.fillMaxSize()){


            var modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center);

            if(errorState.value!!.first) {

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = errorState.value!!.second
                )

            }

            if(loadingState.value!!) {


                LoadingBar(modifier = modifier)

            }


            uiDataList.value?.let {

                Log.d("AshishGupta","0")

                ReusableComposable.LazyColumnView(uiDataList = it)

                NextActivityButton(it)

            }


        }
    }

    @Composable
    fun NextActivityButton(uiDataList:List<Uidata>){


        Log.d("AshishGupta","1")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                    Log.d("AshishGupta","100")
                    gotToSecondActivity(uiDataList)
                },
                modifier = Modifier.padding(all = 10.dp),
                enabled = true,
                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                shape = MaterialTheme.shapes.medium,

                )
            {
                Text(text = "Go to Next Activity")
            }
        }

    }

    fun gotToSecondActivity(uiData:List<Uidata>){

        var intent = Intent(this@MainActivity, SecondActivity::class.java);

        for(i in 0..uiData.size-1){

            uiData[i].key?.let {

                intent.putExtra(it, uiData[i])

            }

        }

        startActivity(intent)
    }

    @Composable
    fun LoadingBar(modifier: Modifier) {

        CircularProgressIndicator(
            // below line is use to add padding
            // to our progress bar.
            modifier = modifier,

            // below line is use to add color
            // to our progress bar.
            color = colorResource(id = R.color.purple_200),

            // below line is use to add stroke
            // width to our progress bar.
            strokeWidth = Dp(value = 4F)
        )

    }
}