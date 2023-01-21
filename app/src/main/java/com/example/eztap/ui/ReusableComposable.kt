package com.example.eztap.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.datalayyer.model.Uidata


object ReusableComposable {

    @Composable
    fun LazyColumnView(uiDataList:List<Uidata>){

        LazyColumn(

            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 200.dp)

        ) {

                items(uiDataList) { data ->
                    CustomItem(itemData = data)
                }

        }

    }

    @Composable
    fun CustomItem(itemData: Uidata){

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
    fun CustomTextField(itemData: Uidata){

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
    fun CustomButton(itemData: Uidata, textStyle: TextStyle){

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