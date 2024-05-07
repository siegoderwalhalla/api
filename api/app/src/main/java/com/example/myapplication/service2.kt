package com.example.myapplication

import retrofit2.http.GET

interface Service2 {
    @GET("/random")
    suspend fun getdate():
            Class2
}