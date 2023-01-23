package com.example.eztap

import android.view.View

object DataStore {

    private val hashMap = LinkedHashMap<String, View>()

    fun addData(key:String, view: View){
        hashMap.put(key,view)
    }

    public fun getMap():HashMap<String,View>{
        return hashMap
    }

}