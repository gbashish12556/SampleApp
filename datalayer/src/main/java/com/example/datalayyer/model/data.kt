package com.example.datalayyer.model

import com.google.gson.annotations.SerializedName

data class UiResponse (

    @SerializedName("logo-url"     ) var logoUrl     : String?           = null,
    @SerializedName("heading-text" ) var headingText : String?           = null,
    @SerializedName("uidata"       ) var uidata       : ArrayList<Uidata> = arrayListOf()

):java.io.Serializable

data class Uidata (

    @SerializedName("uitype" ) var uitype : String? = null,
    @SerializedName("value"  ) var value  : String? = null,
    @SerializedName("key"    ) var key    : String? = null

):java.io.Serializable