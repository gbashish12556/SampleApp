package com.example.eztap.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.datalayyer.model.Uidata
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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

            items(uiDataList.value!!) { data ->
               ListItem(itemData = data)
            }

            item {

                if(errorState.value!!.first) {

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                            text = errorState.value!!.second
                        )
                    }

                }

                if(loadingState.value!!) {

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                            text = "Loading...."
                        )
                    }

                }

            }

        }
    }


    @Composable
    fun ListItem(itemData:Uidata){

        var input by remember { mutableStateOf("") }

        var textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Normal,
            letterSpacing = 0.5.em,
            background = Color.LightGray,
        )

        when(itemData.uitype){

            "label"->{

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = itemData.value!!,
                    style = textStyle
                )

            }
            "edittext"->{

                TextField(
                    value = input,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { input = it },
                    placeholder = {
                        Text(itemData.hint!!)
                    },

                )

            }
            "button"->{

                Button(
                    // below line is use to add onclick
                    // parameter for our button onclick
                    onClick = {
                        //Navigate to next scree
                    },
                    // in below line we are using modifier
                    // which is use to add padding to our button
                    modifier = Modifier.padding(all = 10.dp),

                    // below line is use to set or
                    // button as enable or disable.
                    enabled = true,


                    // below line is use to
                    // add border to our button.
                    border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),

                    // below line is use to add shape for our button.
                    shape = MaterialTheme.shapes.medium,

                )
                // below line is use to
                // add text on our button
                {
                    Text(text = itemData.value!!, color = Color.White, style = textStyle)
                }

            }
        }
    }
}