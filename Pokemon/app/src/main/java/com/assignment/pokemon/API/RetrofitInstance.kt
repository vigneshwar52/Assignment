package com.assignment.pokemon.API

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val URL = "https://pokeapi.co/api/v2/"
    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}