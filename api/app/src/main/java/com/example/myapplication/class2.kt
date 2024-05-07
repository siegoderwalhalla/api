package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Class2(@SerializedName("_id") val id:String, @SerializedName("content") val content: String, @SerializedName("author") val author: String){

}