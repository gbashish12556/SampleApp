package com.example.eztap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.datalayyer.model.Uidata
import com.example.eztap.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.TreeMap

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras;

        if(bundle != null){

            var uiData = mutableListOf<Uidata>()

            var keys = bundle.keySet();

            for(key in keys){

                uiData.add(bundle.get(key) as Uidata)

            }

            setContent {

                Box(modifier = Modifier.fillMaxSize()){

                    ReusableComposable.LazyColumnView(uiDataList = uiData)
                }

            }

        }
    }
}