package com.example.eztap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.contains
import com.example.eztap.DataStore
import com.example.eztap.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    lateinit var mainLayout:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var mainLayout  = findViewById<LinearLayout>(R.id.mainLayout)
        var treeMap = DataStore.getMap()
        var views = treeMap.values

        for(view in views){
            mainLayout.addView(view)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        if(this::mainLayout.isInitialized){
            mainLayout.removeAllViews()
        }

    }
}