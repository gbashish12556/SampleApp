package com.example.eztap.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.datalayyer.model.Uidata
import com.example.eztap.R
import dagger.hilt.android.AndroidEntryPoint


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

            if(errorState.value!!.first) {

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = errorState.value!!.second
                )

            }

            if(loadingState.value!!) {

                CircularProgressIndicator(
                    // below line is use to add padding
                    // to our progress bar.
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),

                    // below line is use to add color
                    // to our progress bar.
                    color = colorResource(id = R.color.purple_200),

                    // below line is use to add stroke
                    // width to our progress bar.
                    strokeWidth = Dp(value = 4F)
                )

            }

            LazyColumn(

                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)

            ) {

                uiDataList.value?.let {listData->
                    items(listData) { data ->
                        CustomItem(itemData = data)
                    }
                }

            }

        }
    }


    @Composable
    fun CustomItem(itemData:Uidata){

        var input by remember { mutableStateOf("") }

        var textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Normal,
            letterSpacing = 0.5.em,
        )

        when(itemData.uitype){

            "label"->{

                CustomTextLabel(itemData = itemData, textStyle=textStyle)

            }
            "edittext"->{

                CustomTextField(itemData = itemData)

            }
            "button"->{

                CustomButton(itemData = itemData, textStyle = textStyle)

            }
        }
    }

    @Composable
    fun CustomTextLabel(itemData: Uidata, textStyle: TextStyle) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = itemData.value!!,
            style = textStyle
        )

    }

    @Composable
    fun CustomTextField(itemData:Uidata){

        var input by remember { mutableStateOf("") }
        TextField(
            value = input,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { input = it },
            placeholder = {
                Text(itemData.hint!!)
            },
        )
    }

    @Composable
    fun CustomButton(itemData:Uidata, textStyle: TextStyle){

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    //Navigate to next scree
                },
                modifier = Modifier.padding(all = 10.dp),
                enabled = true,
                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                shape = MaterialTheme.shapes.medium,

                )
            {
                Text(text = itemData.value!!, color = Color.White, style = textStyle)
            }
        }

    }
}