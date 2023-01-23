package com.example.eztap

import android.view.View
import java.util.SortedMap
import java.util.TreeMap

object DataStore {

    val hashMap = LinkedHashMap<String, View>()

    fun addData(key:String, view: View){
        hashMap.put(key,view)
    }

    public fun getMap():HashMap<String,View>{
        return hashMap
    }

}