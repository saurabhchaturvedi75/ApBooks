package com.example.apbooks

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("books")
    fun getProductData() : Call<MyData>
}