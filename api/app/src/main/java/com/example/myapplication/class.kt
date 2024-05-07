package com.example.myapplication

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class Class1(@SerializedName("type") val type:String, @SerializedName("setup") val setup: String, @SerializedName("punchline") val punchline: String, @SerializedName("id") val id: String){
}
