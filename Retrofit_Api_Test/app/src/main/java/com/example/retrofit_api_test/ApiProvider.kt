package com.example.retrofit_api_test

import retrofit2.Call
import retrofit2.http.GET

interface ApiProvider {

    @GET("/posts")
    fun getPosts(): Call<MutableList<PostModel>>

}